package utebayev.dias.finalprojectjavaadvance.configs;

import utebayev.dias.finalprojectjavaadvance.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home", "/movies", "/serials", "/tvshows","/cartoons", "/about", "/moviePage/**","/serialPage/**","/cartoonPage/**","/showPage/**").permitAll()
                .antMatchers("/new", "/movieAdd","/cartoonAdd","/serialAdd",
                        "/showAdd", "/allusers","/allmovies","/allcartoons","/allshows","/allserials",
                        "/makeUserEditer/**","/makeUserUser/**", "/deleteCartoon/**", "/deleteMovie/**").hasAnyAuthority("ADMIN")
                .antMatchers("/edit/**", "/movieEdit/**","/cartoonEdit/**","/serialEdit/**","/showEdit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/delete/**").hasAuthority("ADMIN")
                .antMatchers("/deleteMyAccount").hasAnyAuthority("USER", "EDITOR")
                .antMatchers("/register" , "/login").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home",true )
                .and()
                .logout().logoutUrl("/logout").permitAll().deleteCookies("JSESSIONID").logoutSuccessUrl("/home")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}

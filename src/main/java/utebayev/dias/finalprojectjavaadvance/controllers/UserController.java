package utebayev.dias.finalprojectjavaadvance.controllers;


import utebayev.dias.finalprojectjavaadvance.entities.User;
import utebayev.dias.finalprojectjavaadvance.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieHandler;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    User saveRole = null;

    @RequestMapping("/userpage")
    public String showUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            return "userpage";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/userEdit")
    public ModelAndView showEditMovieForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername(authentication.getName());
        saveRole = user;
        ModelAndView mav = new ModelAndView("edit_user");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/saveUser")
    public String changeUser(@ModelAttribute("movie") User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRoles(saveRole.getRoles());
        userRepository.save(user);
        return "redirect:/userpage";
    }



    @PostMapping("/deleteMyAccount")
    public String deleteMyAcc(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUserByUsername(authentication.getName());
            userRepository.deleteById(user.getId());
            removeCookies(request,response);
            return "redirect:/logout";
    }

    public static void removeCookies(HttpServletRequest request,
                                     HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];//from w ww  .j  av  a 2 s  .c om
            removeCookie(response, cookie.getName());
        }
    }
    public static void removeCookie(HttpServletResponse response,
                                    String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

package utebayev.dias.finalprojectjavaadvance.controllers;

import utebayev.dias.finalprojectjavaadvance.entities.*;
import utebayev.dias.finalprojectjavaadvance.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CartoonRepository cartoonRepository;
    @Autowired
    private SerialRepository serialRepository;
    @Autowired
    private TVShowRepository tvShowRepository;

    @GetMapping("/home")
    public String homePage(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        Iterable<TVShow> tvShows = tvShowRepository.findAll();
        model.addAttribute("tvshows", tvShows);
        Iterable<Cartoon> cartoons = cartoonRepository.findAll();
        model.addAttribute("cartoons", cartoons);
        Iterable<Serial> serials = serialRepository.findAll();
        model.addAttribute("serials", serials);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @PostMapping("/register") // Map ONLY POST Requests
    public String addNewUser(@ModelAttribute("user") User user) {
        if (userRepository.getUserByUsername(user.getUsername()) != null) {
            return "redirect:/register";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.getRoles().add(new Role("USER"));
        userRepository.save(user);
        return "/login";
    }

    @RequestMapping("/register")
    public String showNewUserForm(Model model) {
            model.addAttribute("user", new User());
            return "new_user";
    }

    @RequestMapping("/about")
    public String showAbout(Model model) {
        return "about";
    }
}

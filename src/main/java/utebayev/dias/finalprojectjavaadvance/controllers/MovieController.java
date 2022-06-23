package utebayev.dias.finalprojectjavaadvance.controllers;


import utebayev.dias.finalprojectjavaadvance.entities.Movie;
import utebayev.dias.finalprojectjavaadvance.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utebayev.dias.finalprojectjavaadvance.services.MovieService;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/moviePage/{id}")
    public String moviePage(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("media", movieService.showEditMovieForm(id));
        return "moviePage";
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        model.addAttribute("movies", movieService.showMovies());
        return "movies";
    }

    @GetMapping("/allmovies")
    public String showAllMovies(Model model) {
        model.addAttribute("movies", movieService.showMovies());
        return "all_movies";
    }

    @RequestMapping("/movieEdit/{id}")
    public ModelAndView showEditMovieForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_movie");
        mav.addObject("movie", movieService.showEditMovieForm(id));
        return mav;
    }
    @RequestMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id) {
        movieService.deleteMovie(id);
        return "redirect:/allmovies";
    }

    @RequestMapping("/movieAdd")
    public String showMovieAdd(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            model.addAttribute("movie", new Movie());
            return "add_movie";
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/movieAdd") // Map ONLY POST Requests
    public String addNewMovie(@ModelAttribute("movie") Movie movie) {
        return movieService.addNewMovie(movie) ? "redirect:/allmovies" : "redirect:/movieAdd";
    }
}

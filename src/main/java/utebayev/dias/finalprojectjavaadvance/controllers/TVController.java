package utebayev.dias.finalprojectjavaadvance.controllers;


import utebayev.dias.finalprojectjavaadvance.entities.TVShow;
import utebayev.dias.finalprojectjavaadvance.repositories.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utebayev.dias.finalprojectjavaadvance.services.ShowService;

@Controller
public class TVController {
    @Autowired
    private ShowService showService;

    @GetMapping("/showPage/{id}")
    public String moviePage(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("media", showService.showEditShowForm(id));
        return "moviePage";
    }

    @GetMapping("/tvshows")
    public String showTV(Model model) {
        model.addAttribute("tvshows", showService.showShows());
        return "tvshows";
    }

    @GetMapping("/allshows")
    public String showAllShows(Model model) {
        model.addAttribute("shows", showService.showShows());
        return "all_tvShows";
    }

    @RequestMapping("/showEdit/{id}")
    public ModelAndView showEditShowForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_show");
        mav.addObject("show", showService.showEditShowForm(id));
        return mav;
    }
    @RequestMapping("/deleteShow/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id) {
        showService.deleteShow(id);
        return "redirect:/allshows";
    }

    @RequestMapping("/showAdd")
    public String showShowAdd(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            model.addAttribute("show", new TVShow());
            return "add_show";
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/showAdd") // Map ONLY POST Requests
    public String addNewShow(@ModelAttribute("show") TVShow tvShow) {
        return  showService.addNewShow(tvShow) ? "redirect:/allshows" : "redirect:/showAdd";
    }
}

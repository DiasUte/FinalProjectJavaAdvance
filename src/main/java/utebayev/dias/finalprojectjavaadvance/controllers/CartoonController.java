package utebayev.dias.finalprojectjavaadvance.controllers;

import utebayev.dias.finalprojectjavaadvance.entities.Cartoon;
import utebayev.dias.finalprojectjavaadvance.entities.Movie;
import utebayev.dias.finalprojectjavaadvance.repositories.CartoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utebayev.dias.finalprojectjavaadvance.services.CartoonService;

@Controller
public class CartoonController {
    @Autowired
    private CartoonService cartoonService;

    @GetMapping("/cartoonPage/{id}")
    public String moviePage(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("media", cartoonService.showEditCartoonForm(id));
        return "moviePage";
    }

    @GetMapping("/cartoons")
    public String showCartoons(Model model) {
        model.addAttribute("cartoons", cartoonService.showCartoons());
        return "cartoons";
    }

    @GetMapping("/allcartoons")
    public String showAllCartoons(Model model) {
        //Iterable<Cartoon> cartoons = cartoonService.showCartoons();
        model.addAttribute("cartoons", cartoonService.showCartoons());
        return "all_cartoons";
    }

    @RequestMapping("/cartoonEdit/{id}")
    public ModelAndView showEditCartoonForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_cartoon");
        mav.addObject("cartoon", cartoonService.showEditCartoonForm(id));
        return mav;
    }
    @RequestMapping("/deleteCartoon/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id) {
        cartoonService.deleteCartoon(id);
        return "redirect:/allcartoons";
    }

    @RequestMapping("/cartoonAdd")
    public String showCartoonAdd(Model model) {
        model.addAttribute("cartoon", new Cartoon());
        return "add_cartoon";
    }

    @PostMapping("/cartoonAdd") // Map ONLY POST Requests
    public String addNewCartoon(@ModelAttribute("cartoon") Cartoon cartoon) {
        return cartoonService.addNewCartoon(cartoon) ? "redirect:/allcartoons" : "redirect:/cartoonAdd";
    }
}

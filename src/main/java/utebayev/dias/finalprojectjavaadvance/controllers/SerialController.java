package utebayev.dias.finalprojectjavaadvance.controllers;

import utebayev.dias.finalprojectjavaadvance.entities.Cartoon;
import utebayev.dias.finalprojectjavaadvance.entities.Serial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utebayev.dias.finalprojectjavaadvance.services.SerialService;

@Controller
public class SerialController {
    @Autowired
    private SerialService serialService;

    @GetMapping("/serialPage/{id}")
    public String moviePage(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("media", serialService.showEditSerialForm(id));
        return "moviePage";
    }

    @GetMapping("/serials")
    public String showSerials(Model model) {
        model.addAttribute("serials", serialService.showSerials());
        return "serials";
    }

    @GetMapping("/allserials")
    public String showAllShows(Model model) {
        model.addAttribute("serials", serialService.showSerials());
        return "all_serials";
    }

    @RequestMapping("/serialEdit/{id}")
    public ModelAndView showEditCartoonForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_serial");
        mav.addObject("serial", serialService.showEditSerialForm(id));
        return mav;
    }
    @RequestMapping("/deleteSerial/{id}")
    public String deleteMovie(@PathVariable(name = "id") Long id) {
        serialService.deleteSerial(id);
        return "redirect:/allserials";
    }

    @RequestMapping("/serialAdd")
    public String showSerialAdd(Model model) {
        model.addAttribute("serial", new Serial());
        return "add_serial";
    }

    @PostMapping("/serialAdd") // Map ONLY POST Requests
    public String addNewSerial(@ModelAttribute("serial") Serial serial) {
        return serialService.addNewSerial(serial) ? "redirect:/allserials" : "redirect:/serialAdd";
    }
}

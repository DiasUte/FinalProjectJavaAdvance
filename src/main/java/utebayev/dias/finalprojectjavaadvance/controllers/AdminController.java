package utebayev.dias.finalprojectjavaadvance.controllers;

import utebayev.dias.finalprojectjavaadvance.entities.*;
import utebayev.dias.finalprojectjavaadvance.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utebayev.dias.finalprojectjavaadvance.services.AdminService;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/allusers")
    public String showAll(Model model) {
        Iterable<User> users = adminService.showAll();
        model.addAttribute("users", users);
        return "show_all_users";
    }

    @RequestMapping("/makeUserEditer/{id}")
    public String makeUserEdit(@PathVariable(value = "id") long id) {
        adminService.makeUserEdit(id);
        return "redirect:/allusers";
    }

    @RequestMapping("/makeUserUser/{id}")
    public String makeUserUser(@PathVariable(value = "id") long id) {
        adminService.makeUserUser(id);
        return "redirect:/allusers";
    }


}

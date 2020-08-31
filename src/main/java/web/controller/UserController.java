package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.getListUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("addUser", new User());
        return "userForm";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("addUser") User user) {
        this.userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("editUser", user);
        return "userEdit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("editUser") User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", userService.getById(id));
        return "userData";
    }

}

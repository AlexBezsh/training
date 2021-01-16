package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@Controller
public class PageController {

    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping(value="/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        log.info("getting all users");
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/register")
    public String regForm(Model model){
        model.addAttribute("user", new User());
        return "reg_form";
    }

}

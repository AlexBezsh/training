package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.service.UserService;

@Slf4j
@Controller
public class PageController {

    private final UserService userService;

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/login")
    public String loginPage() {
        return "index";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        log.info("getting all users");
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/register")
    public String regForm(){
        return "reg_form";
    }

}

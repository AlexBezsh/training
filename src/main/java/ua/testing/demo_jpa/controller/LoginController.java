package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.entity.Role;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.DBException;
import ua.testing.demo_jpa.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/")
    public String home() {
        return "home";
    }

    @GetMapping(value="/login")
    public String loginPage(Model model) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(User user) throws IOException {
        log.info("User is logging in: {}", user);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        try {
            userService.login(user);
        } catch (DBException e) {
            return "redirect:/login?error";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/register")
    public String regForm(Model model){
        if (isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "reg_form";
    }

    @PostMapping(value = "/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reg_form";
        }
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);
        log.info("New user {}", user);
        userService.saveNewUser(user);
        return "redirect:/login";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}

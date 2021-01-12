package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "login")
    public void loginFormController(User user) {
        log.info("User is logging in: {}", user);
        userService.login(user);
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<UserDTO> getAllUsers() {
        log.info("getting all users {}", userService.getAllUsers());
        return userService.getAllUsers();
    }

}

package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/login")
    public void login(User user, HttpServletResponse response) throws IOException {
        log.info("User is logging in: {}", user);
        userService.login(user);
        response.sendRedirect("/users");
    }

}

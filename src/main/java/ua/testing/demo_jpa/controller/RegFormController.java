package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.entity.Role;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class RegFormController {

    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/register")
    public void register(User user, HttpServletResponse response) throws IOException {
        user.setRole(Role.USER);
        log.info("New user {}", user);
        userService.saveNewUser(user);
        response.sendRedirect("/users");
    }

}

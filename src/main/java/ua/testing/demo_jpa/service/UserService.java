package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.DBException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new DBException("There are no users in database.");
        }
        return users
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public void login(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            System.out.println("Everything is ok");
            if (!passwordEncoder.matches(u.getPassword(), passwordEncoder.encode(user.getPassword()))) {
                throw new DBException("Wrong credentials");
            }
        });
    }

    public void saveNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DBException("User with this email is already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}

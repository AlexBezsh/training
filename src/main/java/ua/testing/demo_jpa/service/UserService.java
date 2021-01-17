package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.DBException;
import ua.testing.demo_jpa.repository.UserRepository;

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

    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new DBException("There are no users in database.");
        }
        return users.map(UserDTO::new);
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

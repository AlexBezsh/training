package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exception.DBException;
import ua.testing.demo_jpa.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public UserDTO login(User user) {

        Optional<User> userFromDB = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userFromDB.isPresent()) {
            System.out.println("Everything is OK" + userFromDB);
            return new UserDTO(userFromDB.get());
        }
        throw new DBException("There is no such user in database");
        /*
        return new UserDTO(userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new DBException("There is no such user in database")));*/
    }

    public void saveNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DBException("User with this email is already registered");
        }
        userRepository.save(user);
    }

}

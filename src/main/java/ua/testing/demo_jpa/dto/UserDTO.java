package ua.testing.demo_jpa.dto;

import lombok.*;
import ua.testing.demo_jpa.entity.Role;
import ua.testing.demo_jpa.entity.User;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

    private Long id;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.roles = user.getRoles();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

}

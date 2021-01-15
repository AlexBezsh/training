package ua.testing.demo_jpa.dto;

import lombok.*;
import ua.testing.demo_jpa.entity.Role;
import ua.testing.demo_jpa.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

}

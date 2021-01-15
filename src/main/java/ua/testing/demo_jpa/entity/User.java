package ua.testing.demo_jpa.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50, message = "First name must contain from 2 to 50 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50, message = "Last name must contain from 2 to 50 characters")
    private String lastName;

    @Column(nullable = false)
    @Email(message = "Wrong email")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+).{6,}",
            message = "Password requirements: at least six characters, one digit, " +
                    "one letter in lower case, one letter in upper case")
    private String password;
}

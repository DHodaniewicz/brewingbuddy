package pl.brewingbuddy.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 30, message = "Username has to be at least 4 signs long and maximum of 30 signs long")
    private String username;
    @Email(message = "Has to be an email address")
    private String email;
    @Size(min = 8, message = "Password has to be at least 8 signs long")
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Recipe> recipes;
    private Integer enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

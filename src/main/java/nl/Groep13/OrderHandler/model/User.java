package nl.Groep13.OrderHandler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean defaultpass;

    public User(String name, String email, UserRole role, String password, boolean defaultpass) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.defaultpass = defaultpass;
    }

    public String getEmail() {
        return email;
    }
}

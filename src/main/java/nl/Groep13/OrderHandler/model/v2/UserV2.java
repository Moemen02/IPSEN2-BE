package nl.Groep13.OrderHandler.model.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.Groep13.OrderHandler.model.UserRole;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserV2 {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
    private Long id;
//    @Column(name = "Name")

    private String name;
//    @Column(name = "EMail")

    private String email;
//    @Column(name = "Role")

    @Enumerated(EnumType.STRING)
    private UserRole role;
    //    @Column(name = "Password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @Column(name = "DefaultPass")

    private boolean default_pass;

    public UserV2(String name, String email, UserRole role, String password, boolean default_pass) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.default_pass = default_pass;
    }

    public String getEmail() {
        return email;
    }
}

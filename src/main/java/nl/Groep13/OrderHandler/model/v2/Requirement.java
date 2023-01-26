package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "requirement")
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String requirement;

    public Requirement(String requirement) {
        this.requirement = requirement;
    }
}

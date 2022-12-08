package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "composition")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compositionName;

    public Composition(Long id, String compositionName) {
        this.id = id;
        this.compositionName = compositionName;
    }

    public Composition() {

    }
}

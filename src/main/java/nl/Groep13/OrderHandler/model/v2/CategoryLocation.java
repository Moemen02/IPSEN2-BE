package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Category_location")
public class CategoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location_type;

    //requirementID krijgen hier
//    private Set<Requirement> requirementID;

    public CategoryLocation(Long id, String location_type) {
        this.id = id;
        this.location_type = location_type;
    }

    public CategoryLocation() {
    }
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Category_location")
public class CategoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Location_type;

    public CategoryLocation(Long ID, String location_type) {
        this.ID = ID;
        Location_type = location_type;
    }

    public CategoryLocation() {
    }
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Category_location")
public class CategoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String location_type;

//    @ElementCollection
    private String requirementID;

    public CategoryLocation(Long ID, String location_type) {
        this.ID = ID;
        this.location_type = location_type;
    }

    public CategoryLocation() {
    }
}

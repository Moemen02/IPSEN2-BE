package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@Setter
@Table(name = "Category_location")
public class CategoryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location_type;

//    @ElementCollection
    private String requirementID;

    public CategoryLocation(Long id, String location_type) {
        this.id = id;
        this.location_type = location_type;
    }

    public CategoryLocation() {
    }
}

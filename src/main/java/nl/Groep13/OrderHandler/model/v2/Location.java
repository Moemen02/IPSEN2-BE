package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Long Category_locationID;
    private Long CompositionID;
    private Long RequirementID;

    public Location(Long ID, Long category_locationID, Long compositionID, Long requirementID) {
        this.ID = ID;
        Category_locationID = category_locationID;
        CompositionID = compositionID;
        RequirementID = requirementID;
    }

    public Location() {
    }
}

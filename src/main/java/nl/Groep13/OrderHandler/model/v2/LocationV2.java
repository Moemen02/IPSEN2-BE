package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Location")
public class LocationV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Long Category_locationID;
    private String Composition;
    private Long RequirementID;

    public LocationV2(Long ID, Long category_locationID, String composition, Long requirementID) {
        this.ID = ID;
        Category_locationID = category_locationID;
        Composition = composition;
        RequirementID = requirementID;
    }

    public LocationV2() {
    }
}

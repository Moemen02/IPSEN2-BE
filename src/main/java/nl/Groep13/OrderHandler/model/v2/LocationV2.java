package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Location")
public class LocationV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long category_locationID;
    private String composition;
    private Long requirementID;

    public LocationV2(Long id, Long category_locationID, String composition, Long requirementID) {
        this.id = id;
        this.category_locationID = category_locationID;
        this.composition = composition;
        this.requirementID = requirementID;
    }

    public LocationV2() {
    }
}

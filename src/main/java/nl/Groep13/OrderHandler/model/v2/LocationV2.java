package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "location")
public class LocationV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long category_locationID;

    public LocationV2(Long id, Long category_locationID) {
        this.id = id;
        this.category_locationID = category_locationID;
    }

    public LocationV2() {
    }
}

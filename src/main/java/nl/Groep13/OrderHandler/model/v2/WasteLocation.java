package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "article_location")
public class WasteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "locationID", referencedColumnName = "id")
    private LocationV2 locationId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "WasteID", referencedColumnName = "id")
    private Waste wasteId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "UsageID", referencedColumnName = "id")
    private Usage usageId;
}

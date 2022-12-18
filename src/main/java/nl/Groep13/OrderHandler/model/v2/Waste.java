package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "waste")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Waste_dataID", referencedColumnName = "ID")
    private WasteData Waste_dataID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Waste_descriptionID", referencedColumnName = "ID")
    private WasteDescription Waste_descriptionID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UsageID", referencedColumnName = "ID")
    private Usage UsageID;
}

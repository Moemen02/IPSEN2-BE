package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name = "waste")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long Waste_dataID;
    private Long Waste_descriptionID;
    private Long usageID;
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "waste_description")
public class WasteDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String articleNumber;
    private String description;
    //clothWidth = Stofbreedte
    private int clothWidth;
    private String type;
    private String layout;
    private String washcode;
    private int weight;
    private boolean not_tiltable;
    //minimumStock = Min
    private int minimumStock;


    public WasteDescription(Long id, String articleNumber, String description, int clothWidth, String type, String layout, String washcode, int weight, boolean not_tiltable, int minimumStock) {
        this.id = id;
        this.articleNumber = articleNumber;
        this.description = description;
        this.clothWidth = clothWidth;
        this.type = type;
        this.layout = layout;
        this.washcode = washcode;
        this.weight = weight;
        this.not_tiltable = not_tiltable;
        this.minimumStock = minimumStock;
    }

    public WasteDescription() {

    }
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Table(name = "waste_data")
public class WasteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplier;
    private String productgroup;
    private String eancode;
    private String color;
    private float patternLength;
    private float patternWidth;
    private String composition;
    private boolean stockRL;

    public WasteData(Long id, String supplier, String eancode, String color, float patternLength, float patternWidth, String composition, boolean stockRL, String productgroup) {
        this.id = id;
        this.supplier = supplier;
        this.eancode = eancode;
        this.color = color;
        this.patternLength = patternLength;
        this.patternWidth = patternWidth;
        this.composition = composition;
        this.stockRL = stockRL;
        this.productgroup = productgroup;
    }

    public WasteData() {

    }
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "waste_data")
public class WasteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplier;
    private String productGroup;
    private String eancode;
    private Long colorId;
    private float patternLength;
    private float patternWidth;
    private Long compositionId;
    private boolean stockRL;

    public WasteData(Long id, String supplier, String productGroup, String eancode, Long colorId, float patternLength, float patternWidth, Long compositionId, boolean stockRL) {
        this.id = id;
        this.supplier = supplier;
        this.productGroup = productGroup;
        this.eancode = eancode;
        this.colorId = colorId;
        this.patternLength = patternLength;
        this.patternWidth = patternWidth;
        this.compositionId = compositionId;
        this.stockRL = stockRL;
    }

    public WasteData() {

    }
}

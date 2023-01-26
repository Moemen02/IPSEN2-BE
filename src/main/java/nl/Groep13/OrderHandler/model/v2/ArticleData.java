package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.Groep13.OrderHandler.interfaces.UpdateIncludeAttribute;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@Table(name = "article_data")
public class ArticleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String supplier;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String productgroup;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String eancode;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String color;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.IsFloat)
    private float patternLength;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.IsFloat)
    private float patternWidth;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String composition;
    private boolean stockRL;

    public ArticleData(Long id, String supplier, String eancode, String color, float patternLength, float patternWidth, String composition, boolean stockRL, String productgroup) {
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

    public ArticleData() {

    }
}

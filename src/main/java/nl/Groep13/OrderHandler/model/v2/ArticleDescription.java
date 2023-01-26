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
@Table(name = "article_description")
public class ArticleDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String articlenumber;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String description;
    //clothWidth = Stofbreedte
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.IsInt)
    private int clothWidth;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String type;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String layout;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private String washcode;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.IsInt)
    private int weight;
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.Null)
    private boolean not_tiltable;
    //minimumStock = Min
    @UpdateIncludeAttribute(CheckWhich = UpdateIncludeAttribute.CheckWhich.IsInt)
    private int minimumStock;


    public ArticleDescription(Long id, String articlenumber, String description, int clothWidth, String type, String layout, String washcode, int weight, boolean not_tiltable, int minimumStock) {
        this.id = id;
        this.articlenumber = articlenumber;
        this.description = description;
        this.clothWidth = clothWidth;
        this.type = type;
        this.layout = layout;
        this.washcode = washcode;
        this.weight = weight;
        this.not_tiltable = not_tiltable;
        this.minimumStock = minimumStock;
    }

    public ArticleDescription() {

    }
}

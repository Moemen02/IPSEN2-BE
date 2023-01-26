package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "article_order")
public class ArticleOrder {

    //Eigenaar van het materiaal, oftewel de klant
    //Ordernummer
    //Aantal meter restant
    //Stofnaam, -kleur en -samenstelling

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articleID", referencedColumnName = "id")
    private ArticleV2 articleID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "id")
    private CustomerV2 customerID;
    private boolean finished;

    public ArticleOrder(Long id, ArticleV2 articleID, CustomerV2 customerID, boolean finished) {
        this.id = id;
        this.articleID = articleID;
        this.customerID = customerID;
        this.finished = finished;
    }

    public ArticleOrder() {
    }
}

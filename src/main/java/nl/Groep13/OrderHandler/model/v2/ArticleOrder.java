package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "article_order")
public class ArticleOrder {

    //Eigenaar van het materiaal, oftewel de klant
    //Ordernummer
    //Aantal meter restant
    //Stofnaam, -kleur en -samenstelling

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ArticleID", referencedColumnName = "ID")
    private Long articleID;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "CustomerID", referencedColumnName = "ID")
    private Long customerID;
    private boolean finished;

    public ArticleOrder(Long id, Long articleID, Long customerID, boolean finished) {
        this.id = id;
        this.articleID = articleID;
        this.customerID = customerID;
        this.finished = finished;
    }

    public ArticleOrder() {
    }
}

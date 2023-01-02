package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "article")
public class ArticleV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_dataID", referencedColumnName = "ID")
    private ArticleData article_dataID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_descriptionID", referencedColumnName = "ID")
    private ArticleDescription article_descriptionID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "UsageID", referencedColumnName = "ID")
    private Usage usageID;

    public ArticleV2() {

    }
}

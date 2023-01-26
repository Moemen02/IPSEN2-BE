package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "article_location")
public class ArticleLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long locationID;
    private Long articleID;
    private Long usageID;

    public ArticleLocation( Long locationID, Long articleID, Long usageID) {
        this.locationID = locationID;
        this.articleID = articleID;
        this.usageID = usageID;
    }
}

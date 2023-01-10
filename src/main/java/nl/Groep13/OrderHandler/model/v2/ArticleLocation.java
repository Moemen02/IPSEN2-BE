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
    private Long wasteID;
    private Long usageID;

    public ArticleLocation(Long locationID, Long wasteID, Long usageID) {
        this.locationID = locationID;
        this.wasteID = wasteID;
        this.usageID = usageID;
    }
}

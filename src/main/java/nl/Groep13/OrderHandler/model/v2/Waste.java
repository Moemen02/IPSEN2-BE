package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "article")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_dataID", referencedColumnName = "id")
    private WasteData article_dataID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "article_descriptionID", referencedColumnName = "id")
    private WasteDescription article_descriptionID;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usageID", referencedColumnName = "id")
    private Usage UsageID;

    public Waste() {

    }
}

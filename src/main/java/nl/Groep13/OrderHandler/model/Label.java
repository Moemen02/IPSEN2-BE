package nl.Groep13.OrderHandler.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "label")
public class Label {

    @Id
    private Long id;
    private Long locationid;
    private Long orderid;

    public Label(Long locationid, Long orderid) {
        this.locationid = locationid;
        this.orderid = orderid;
    }
}

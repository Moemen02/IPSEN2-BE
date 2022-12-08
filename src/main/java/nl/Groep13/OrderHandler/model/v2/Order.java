package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long wasteID;
    private Long customerID;
    private boolean finished;

    public Order(Long id, Long wasteID, Long customerID, boolean finished) {
        this.id = id;
        this.wasteID = wasteID;
        this.customerID = customerID;
        this.finished = finished;
    }

    public Order() {
    }
}

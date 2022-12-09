package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Lorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Long WasteID;
    private Long CustomerID;
    private boolean Finished;

    public Order(Long ID, Long wasteID, Long customerID, boolean finished) {
        this.ID = ID;
        WasteID = wasteID;
        CustomerID = customerID;
        Finished = finished;
    }

    public Order() {
    }
}

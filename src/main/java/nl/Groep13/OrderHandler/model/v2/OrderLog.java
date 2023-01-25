package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_log")
public class OrderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderID;
    private Long userID;

    public OrderLog(Long orderId, Long userId) {
        this.orderID = orderId;
        this.userID = userId;
    }
}

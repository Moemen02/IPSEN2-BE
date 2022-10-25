package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository){

        this.orderRepository = orderRepository;
    }


}

package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.lOrder;
import nl.Groep13.OrderHandler.repository.OrderRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository){

        this.orderRepository = orderRepository;
    }

    public List<lOrder> getAllOrders(){
        List<lOrder> orders = this.orderRepository.findAll();

        return orders;
    }

}

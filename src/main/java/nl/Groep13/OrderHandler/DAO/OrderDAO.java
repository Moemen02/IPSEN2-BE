package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.lOrder;
import nl.Groep13.OrderHandler.repository.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

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

    public Optional<lOrder> getOrderById(long id) throws ChangeSetPersister.NotFoundException{
        Optional<lOrder> order = this.orderRepository.findById(id);

        return order;
    }

    public Optional<lOrder> updateOrder(Long id, Optional<lOrder> order){
        Optional<lOrder> oldOrderById = orderRepository.findById(id);
        lOrder oldOrder = oldOrderById.get();
        lOrder newOrder = order.get();

        newOrder.setId((newOrder.getId() == null) ? oldOrder.getId() : newOrder.getId());
        newOrder.setArticlenumber((newOrder.getArticlenumber() == 0) ? oldOrder.getArticlenumber() : newOrder.getArticlenumber());
        newOrder.setCustomerid((newOrder.getCustomerid() == 0) ? oldOrder.getCustomerid() : newOrder.getCustomerid());
        newOrder.setClaimed_by((newOrder.getClaimed_by() == 0) ? oldOrder.getClaimed_by() : newOrder.getClaimed_by());

        orderRepository.setOrderById(
                newOrder.getId(),
                newOrder.getArticlenumber(),
                newOrder.getCustomerid(),
                newOrder.getClaimed_by()
        );

        orderRepository.save(newOrder);
        return order;
    }

    public void deleteOrder(final Long id) throws ChangeSetPersister.NotFoundException {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deletelOrderById(id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public lOrder addOrder(final lOrder order){
        order.setId(null);
        return this.orderRepository.save(order);
    }
}

package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<lOrder> getAllOrders(){
        return this.orderDAO.getAllOrders();
    }

    public Optional<lOrder> getOrderById(Long id) throws ChangeSetPersister.NotFoundException{
        return this.orderDAO.getOrderById(id);
    }

    public  Optional<lOrder> updateOrder(Long id, Optional<lOrder> order){
        return this.orderDAO.updateOrder(id, order);
    }

    public void deleteOrder(final Long id) throws ChangeSetPersister.NotFoundException{
        this.orderDAO.deleteOrder(id);
    }

    public lOrder addOrder(final lOrder order){
        return this.orderDAO.addOrder(order);
    }
}
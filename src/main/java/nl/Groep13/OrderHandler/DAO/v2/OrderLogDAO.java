package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.OrderLogInterface;
import nl.Groep13.OrderHandler.model.v2.OrderLog;
import nl.Groep13.OrderHandler.repository.v2.OrderLogRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderLogDAO implements OrderLogInterface {

    private final OrderLogRepository orderLogRepository;

    public OrderLogDAO(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    @Override
    public List<OrderLog> getAllOrderLogs() {
        return this.orderLogRepository.findAll();
    }

    @Override
    public OrderLog getOrderLogById(Long id) {
        return null;
    }

    @Override
    public OrderLog saveOrder(OrderLog orderLog) {
        return orderLogRepository.save(orderLog);
    }
}

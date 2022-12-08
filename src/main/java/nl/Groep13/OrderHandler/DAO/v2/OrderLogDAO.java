package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.OrderLogInterface;
import nl.Groep13.OrderHandler.model.v2.OrderLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderLogDAO implements OrderLogInterface {
    @Override
    public List<OrderLog> getAllOrderLogs() {
        return null;
    }

    @Override
    public OrderLog getOrderLogById(Long id) {
        return null;
    }

    @Override
    public OrderLog saveOrder(OrderLog orderLog) {
        return null;
    }
}

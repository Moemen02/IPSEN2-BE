package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.OrderLog;

import java.util.List;

public interface OrderLogInterface {

    List<OrderLog> getAllOrderLogs();
    OrderLog getOrderLogById(Long id);
    OrderLog saveOrder(OrderLog orderLog);

}


package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/orders")
public class OrderController {

    private OrderDAO orderDAO;
    private lOrder lOrder;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}

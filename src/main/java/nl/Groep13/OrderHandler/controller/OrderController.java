package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/orders")
public class OrderController {

    private OrderDAO orderDAO;
    private lOrder lorder;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<lOrder> getAllOrders(){
        List<lOrder> orders = this.orderDAO.getAllOrders();

        return orders;
    }
}

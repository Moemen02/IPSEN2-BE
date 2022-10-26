package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.OrderDAO;
import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Optional<lOrder> getOrderById(@PathVariable Long id){
        Optional<lOrder> order = this.orderDAO.getOrderById(id);

        return order;
    }
}

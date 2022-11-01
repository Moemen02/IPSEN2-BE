package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.lOrder;
import nl.Groep13.OrderHandler.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/orders")
public class OrderController {

    private lOrder lorder;
    private final OrderService orderService;
    Gson gson = new Gson();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<lOrder> getAllOrders(){
        List<lOrder> orders = this.orderService.getAllOrders();

        return orders;
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Optional<lOrder> getOrderById(@PathVariable Long id){
        Optional<lOrder> order = this.orderService.getOrderById(id);

        return order;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public Optional<lOrder> updateOrder(@PathVariable Long id, @RequestBody Map<String, String> order){

        String orderToJson = gson.toJson(order);
        lOrder newOrder = gson.fromJson(orderToJson, lOrder.class);

        return this.orderService.updateOrder(id, Optional.of(newOrder));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteOrder(@PathVariable final Long id) {
        try {
            orderService.deleteOrder((Long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Boolean> addOrder(@RequestParam Map<String, String> order) {
        String orderToJson = gson.toJson(order);
        lOrder newOrder = gson.fromJson(orderToJson, lOrder.class);
        if (this.orderService.addOrder(newOrder) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}

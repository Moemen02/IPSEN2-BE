package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.controller.UserController;
import nl.Groep13.OrderHandler.interfaces.OrderLogInterface;
import nl.Groep13.OrderHandler.model.User;
import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import nl.Groep13.OrderHandler.model.v2.OrderLog;
import org.hibernate.criterion.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2/order/log")
public class OrderLogController {

    private final OrderLogInterface orderLogInterface;
    private final UserController userController;

    public OrderLogController(OrderLogInterface orderLogInterface, UserController userController) {
        this.orderLogInterface = orderLogInterface;
        this.userController = userController;
    }

    @GetMapping
    public ResponseEntity<String> getOrderLog(){
        return ResponseEntity.ok().body("dit moet een order log item zijn");
    }

    @PostMapping
    public ResponseEntity<OrderLog> saveOrderLog(@RequestBody ArticleOrder order, Authentication authentication){
        User user = this.userController.getAuthUser(authentication);
        System.out.println(order);
        OrderLog orderLog = new OrderLog(order.getId(), user.getId());
        return ResponseEntity.ok(this.orderLogInterface.saveOrder(orderLog));
    }
}

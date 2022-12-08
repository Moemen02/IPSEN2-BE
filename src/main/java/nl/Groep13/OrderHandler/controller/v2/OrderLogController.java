package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.OrderLogInterface;
import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/order/log")
public class OrderLogController {

    private final OrderLogInterface orderLogInterface;

    public OrderLogController(OrderLogInterface orderLogInterface) {
        this.orderLogInterface = orderLogInterface;
    }

    @GetMapping
    public ResponseEntity<String> getOrderLog(){
        return ResponseEntity.ok().body("dit moet een order log item zijn");
    }
}

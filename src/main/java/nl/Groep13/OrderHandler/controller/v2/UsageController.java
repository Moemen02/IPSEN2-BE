package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.UsageInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/usage")
public class UsageController {

    private final UsageInterface usageInterface;

    public UsageController(UsageInterface usageInterface1){
        this.usageInterface = usageInterface1;
    }

    @GetMapping
    public ResponseEntity<String> getUsage(){
        return ResponseEntity.ok().body("dit moet een usage item zijn");
    }
}

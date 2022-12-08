package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/waste/location")
public class WasteLocationController {

    private final WasteLocationInterface wasteLocationInterface;

    public WasteLocationController(WasteLocationInterface wasteLocationInterface) {
        this.wasteLocationInterface = wasteLocationInterface;
    }

    @GetMapping
    public ResponseEntity<String> getWasteLocation(){
        return ResponseEntity.ok().body("dit moet een wastelocation item zijn");
    }
}

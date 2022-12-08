package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/waste")
public class WasteController {
    private final WasteInterface wasteInterface;

    public WasteController(WasteInterface wasteInterface) {
        this.wasteInterface = wasteInterface;
    }

    @GetMapping
    public ResponseEntity<String> getWaste(){
        return ResponseEntity.ok().body("dit moet een waste item zijn");
    }
}

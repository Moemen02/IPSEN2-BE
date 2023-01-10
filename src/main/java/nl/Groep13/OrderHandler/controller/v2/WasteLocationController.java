package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.model.v2.WasteLocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/waste/location")
public class WasteLocationController {

    private final WasteLocationInterface wasteLocationInterface;

    public WasteLocationController(WasteLocationInterface wasteLocationInterface) {
        this.wasteLocationInterface = wasteLocationInterface;
    }

    @GetMapping(value = "/article/{id}")
    public WasteLocation getWasteLocationByArticle(@PathVariable Long id){
        WasteLocation wasteLocationWasteId = wasteLocationInterface.getWasteLocationById(id);
        System.out.println(wasteLocationWasteId);
        return null;
    }




    @GetMapping
    public ResponseEntity<String> getWasteLocation(){
        return ResponseEntity.ok().body("dit moet een wastelocation item zijn");
    }
}

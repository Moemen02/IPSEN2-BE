package nl.Groep13.OrderHandler.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.xml.bind.v2.TODO;
import nl.Groep13.OrderHandler.model.v2.WasteOrder;
import nl.Groep13.OrderHandler.repository.v2.WasteOrderRepository;
import nl.Groep13.OrderHandler.service.V2.WasteOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/waste_order")
public class WasteOrderController {

    private WasteOrderService wasteOrderService;

    private ObjectMapper objectMapper;

    Gson gson = new Gson();

    @Autowired

    public WasteOrderController(WasteOrderService wasteOrderService, ObjectMapper objectMapper, Gson gson) {
        this.wasteOrderService = wasteOrderService;
        this.objectMapper = objectMapper;
        this.gson = gson;
    }

    @GetMapping(value = "/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<WasteOrder>> getWasteOrderById(@PathVariable Long ID){
        try{
            Optional<WasteOrder> wasteOrder = this.wasteOrderService.getWasteOrderById(ID);
            return new ResponseEntity<>(wasteOrder, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<WasteOrder>> getAllWasteOrders() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                this.wasteOrderService.getAllWasteOrders()
        );
    }

    //TODO UPDATE FUNCTIE NOG SCHRIJVEN
}

package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDAO;
import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste")
public class WasteController {
    private final WasteDAO wasteDAO;

    public WasteController(WasteDAO wasteDAO) {
        this.wasteDAO = wasteDAO;
    }

    @GetMapping
    public ResponseEntity<List<Waste>> getWaste(){
        return ResponseEntity.ok().body(this.wasteDAO.getWaste());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Waste> getWasteById(@PathVariable Long id) {
        try {
            Waste checkedWaste = this.wasteDAO.getWasteById(id);
            return new ResponseEntity<>(checkedWaste, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Waste> updateWaste(@PathVariable final Long id, @RequestBody final Waste waste) throws ChangeSetPersister.NotFoundException {
        try {
            this.wasteDAO.getWasteById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(
                wasteDAO.updateWaste(id, waste)
        );
        //TODO check for a simpler implementation
    }

}

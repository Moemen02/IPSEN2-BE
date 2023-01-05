package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDescriptionDAO;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.model.v2.WasteDescription;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste_description")
public class WasteDescriptionController {
    private final WasteDescriptionDAO wasteDescriptionDAO;

    public WasteDescriptionController(WasteDescriptionDAO wasteDescriptionDAO) {
        this.wasteDescriptionDAO = wasteDescriptionDAO;
    }

    @GetMapping
    public ResponseEntity<List<WasteDescription>> getAllWasteDescription() {
        return ResponseEntity.ok(
                this.wasteDescriptionDAO.getAllWasteDescription()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<WasteDescription> getWasteDescriptionById(@PathVariable Long id) {
        try {
            WasteDescription checkedWasteDescription = this.wasteDescriptionDAO.getWasteDescriptionById(id);
            return new ResponseEntity<>(checkedWasteDescription, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<WasteDescription> addWasteDescription(@RequestBody WasteDescription wasteDescription) {
        if (this.wasteDescriptionDAO.addWasteDescription(wasteDescription) == null) {
            throw new NullPointerException("WasteData is empty!");
        } else {
            return ResponseEntity.ok(wasteDescription);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WasteDescription> updateWasteDescription(@PathVariable final Long id, @RequestBody final WasteDescription wasteDescription) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        try {
            wasteDescriptionDAO.getWasteDescriptionById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(
                wasteDescriptionDAO.updateWasteDescription(id, wasteDescription)
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWasteDescription(@PathVariable final Long id) {
        try {
            wasteDescriptionDAO.deleteWasteDescriptionById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }
}

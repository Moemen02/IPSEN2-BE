package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDataDAO;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/waste_data")
public class WasteDataController {
    private final WasteDataDAO wasteDataDAO;

    public WasteDataController(WasteDataDAO wasteDataDAO) {
        this.wasteDataDAO = wasteDataDAO;
    }

    @GetMapping
    public ResponseEntity<List<WasteData>> getAllWasteData() {
        return ResponseEntity.ok(
          this.wasteDataDAO.getAllWasteData()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<WasteData> getWasteDataById(@PathVariable Long id) {
        try {
            WasteData checkedWasteData = this.wasteDataDAO.getWasteDataById(id);
            return new ResponseEntity<>(checkedWasteData, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<WasteData> addWasteData(@RequestBody WasteData wasteData) {
        if (this.wasteDataDAO.addWasteData(wasteData) == null) {
            throw new NullPointerException("WasteData is empty!");
        } else {
            return ResponseEntity.ok(wasteData);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WasteData> updateWasteData(@PathVariable final Long id, @RequestBody final WasteData wasteData) throws ChangeSetPersister.NotFoundException {
        try {
            wasteDataDAO.getWasteDataById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(
                wasteDataDAO.updateWasteData(id, wasteData)
        );
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteWasteData(@PathVariable final Long id) {
        try {
            wasteDataDAO.deleteWasteDataById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}

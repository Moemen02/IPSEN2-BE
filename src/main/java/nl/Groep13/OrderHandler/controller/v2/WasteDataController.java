package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDataDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleData;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/article_data")
public class WasteDataController {
    private final WasteDataDAO wasteDataDAO;

    public WasteDataController(WasteDataDAO wasteDataDAO) {
        this.wasteDataDAO = wasteDataDAO;
    }

    @GetMapping
    public ResponseEntity<List<ArticleData>> getAllWasteData() {
        return ResponseEntity.ok(
          this.wasteDataDAO.getAllWasteData()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleData> getWasteDataById(@PathVariable Long id) {
        try {
            ArticleData checkedWasteData = this.wasteDataDAO.getWasteDataById(id);
            return new ResponseEntity<>(checkedWasteData, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ArticleData> addWasteData(@RequestBody ArticleData wasteData) {
        if (this.wasteDataDAO.addWasteData(wasteData) == null) {
            throw new NullPointerException("WasteData is empty!");
        } else {
            return ResponseEntity.ok(wasteData);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArticleData> updateWasteData(@PathVariable final Long id, @RequestBody final ArticleData wasteData) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        try {
            wasteDataDAO.getWasteDataById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(
                wasteDataDAO.updateWasteData(id, wasteData)
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWasteData(@PathVariable final Long id) {
        try {
            wasteDataDAO.deleteWasteDataById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }
}

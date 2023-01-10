package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDAO;
import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.repository.v2.WasteRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/article")
public class WasteController {
    private final WasteDAO wasteDAO;
//    private final WasteInterface wasteInterface = null;

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

    @PostMapping
    public ResponseEntity<Waste> addWaste (@RequestBody final Waste waste) throws ChangeSetPersister.NotFoundException {
        if (waste == null) {
            throw new NullPointerException("Waste is empty");
        } else if (waste.getArticle_dataID() == null) {
            throw new NullPointerException("WasteData is empty");
        } else if (waste.getArticle_descriptionID() == null) {
            throw new NullPointerException("WasteDescription is empty");
        } else if (waste.getUsageID() == null) {
            throw new NullPointerException("Usage is empty");
        } else {
            wasteDAO.addWaste(waste);
            return ResponseEntity.ok(waste);
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
    }
}

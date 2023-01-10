package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDAO;
import nl.Groep13.OrderHandler.model.v2.Waste;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import nl.Groep13.OrderHandler.utils.Pager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste")
public class WasteController {
    private final WasteDAO wasteDAO;

    public WasteController(WasteDAO wasteDAO ) {
        this.wasteDAO = wasteDAO;
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
    public ResponseEntity<Waste> addWaste(@RequestBody final Waste waste) throws ChangeSetPersister.NotFoundException {
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

    @GetMapping
    public ResponseEntity<List<Waste>> getWaste(@RequestParam int page) {

        List<Waste> fullList = this.wasteDAO.getWaste();
        List<Waste> toSend = Pager.getRequestedItems(fullList, page);

        return ResponseEntity.ok()
                .headers(Pager.addHeaders(fullList.size()))
                .body(toSend);
    }
}

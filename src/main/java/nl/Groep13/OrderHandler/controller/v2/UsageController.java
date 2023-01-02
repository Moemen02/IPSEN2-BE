package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.UsageDAO;
import nl.Groep13.OrderHandler.model.v2.Usage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/usage")
public class UsageController{

    private final UsageDAO usageDAO;

    public UsageController(UsageDAO usageDAO){
        this.usageDAO = usageDAO;
    }

    @GetMapping
    public ResponseEntity<List<Usage>> getAllUsages() {
        return ResponseEntity.ok(
                this.usageDAO.getAllUsages()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Usage> getUsageById(@PathVariable Long id) {
        try {
            Usage checkedUsage = this.usageDAO.getUsageById(id);
            return new ResponseEntity<>(checkedUsage, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Usage> addUsage(@RequestBody Usage usage) {
        if (this.usageDAO.addUsage(usage) == null) {
            throw new NullPointerException("Usage is empty!");
        } else {
            return ResponseEntity.ok(usage);
        }
    }
}

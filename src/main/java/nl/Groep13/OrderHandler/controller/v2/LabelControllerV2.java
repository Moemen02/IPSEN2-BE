package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.LabelInterface;
import nl.Groep13.OrderHandler.model.v2.LabelV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v2/label")
public class LabelControllerV2 {

    private final LabelInterface labelInterface;


    public LabelControllerV2(LabelInterface labelInterface) {
        this.labelInterface = labelInterface;
    }

    @GetMapping()
    public ResponseEntity<List<LabelV2>> getAllLabels() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(this.labelInterface.getLabel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LabelV2>> getLabelById(@PathVariable Long id){
        return ResponseEntity.ok(this.labelInterface.getLabelById(id));
    }
}

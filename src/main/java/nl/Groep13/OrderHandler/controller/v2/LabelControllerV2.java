package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.LabelInterface;
import nl.Groep13.OrderHandler.model.v2.LabelV2;
import nl.Groep13.OrderHandler.service.V2.LabelDataServiceV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v2/label")
public class LabelControllerV2 {

    private final LabelInterface labelInterface;
    private final LabelDataServiceV2 labelDataServiceV2;


    public LabelControllerV2(LabelInterface labelInterface, LabelDataServiceV2 labelControllerV2) {
        this.labelInterface = labelInterface;
        this.labelDataServiceV2 = labelControllerV2;
    }

    @GetMapping()
    public ResponseEntity<List<LabelV2>> getAllLabels() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(this.labelInterface.getLabel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LabelV2>> getLabelById(@PathVariable Long id){
        return ResponseEntity.ok(this.labelInterface.getLabelById(id));
    }


    @GetMapping(value = "/data/{id}")
    @ResponseBody
    public Map<String,String> getLabelData(@PathVariable Long id) throws Exception {
//        return labelDataServiceV2.getLabelData(id);
        return labelDataServiceV2.getLabelImage(id);
    }

    @GetMapping(value = "/pdf/{id}")
    @ResponseBody
    public Map <String, String> getPDF(@PathVariable Long id) throws  Exception{
        return labelDataServiceV2.getPDFData(id);
    }
}

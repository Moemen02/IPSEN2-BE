package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.service.LabelService;
import nl.Groep13.OrderHandler.service.LabelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/label")
public class LabelController {
   private final LabelService labelService;
   private LabelDataService labelDataService;

    Gson gson = new Gson();

    @Autowired
    public LabelController(LabelService labelService, LabelDataService labelDataService) {
        this.labelService = labelService;
        this.labelDataService = labelDataService;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public HashMap<String,String> getLabelData(@PathVariable Long id) throws FileNotFoundException, ChangeSetPersister.NotFoundException {
       return labelDataService.getLabelData(id);
    }

}

package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.service.LabelService;
import nl.Groep13.OrderHandler.service.MakeExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/label")
public class LabelController {
   private final LabelService labelService;
   private MakeExcelService makeExcelService;

    Gson gson = new Gson();

    @Autowired
    public LabelController(LabelService labelService, MakeExcelService makeExcelService) {
        this.labelService = labelService;
        this.makeExcelService = makeExcelService;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public HashMap<String,String> getLabelData(@PathVariable Long id) throws FileNotFoundException, ChangeSetPersister.NotFoundException {
       return makeExcelService.getLabelData(id);

    }

//    @GetMapping
//    public resp



}

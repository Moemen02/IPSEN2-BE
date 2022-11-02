package nl.Groep13.OrderHandler.controller;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/label")
public class LabelController {
   private final LabelService labelService;

    Gson gson = new Gson();

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

//    @GetMapping
//    public resp



}

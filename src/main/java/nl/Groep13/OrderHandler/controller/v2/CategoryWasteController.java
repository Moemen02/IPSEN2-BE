package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.model.v2.Category;
import nl.Groep13.OrderHandler.model.v2.WasteData;
import nl.Groep13.OrderHandler.record.CompositionRequest;
import nl.Groep13.OrderHandler.service.V2.CategoryWasteService;
import nl.Groep13.OrderHandler.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/category-waste")
public class CategoryWasteController {

    @Autowired
    private CategoryWasteService categoryWasteService;

    @GetMapping("/colors")
    public List<String> getAllDistinctColors() {
        return categoryWasteService.getDistinctByColor();
    }

    @PostMapping("/composition")
    public ResponseEntity<List<Category>> getSKU(@RequestBody CompositionRequest compositionRequest, @RequestParam int page) {

        List<Category> allData = categoryWasteService.calcAllWaste(compositionRequest);
        List<Category> dataToSend = Pager.getRequestedItems(allData, page);

        return ResponseEntity.ok()
                .headers(Pager.addHeaders(allData.size()))
                .body(dataToSend);
    }
}

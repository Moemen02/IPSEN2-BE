package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteInterface;
import nl.Groep13.OrderHandler.model.v2.Waste;
import nl.Groep13.OrderHandler.utils.Pager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste")
public class WasteController {
    private final WasteInterface wasteInterface;

    public WasteController(WasteInterface wasteInterface) {
        this.wasteInterface = wasteInterface;
    }

    @GetMapping
    public ResponseEntity<List<Waste>> getWaste(@RequestParam int page){

        List<Waste> fullList = this.wasteInterface.getWaste();
        List<Waste> toSend = Pager.getRequestedItems(fullList, page);

        return ResponseEntity.ok()
                .headers(Pager.addHeaders(fullList.size()))
                .body(toSend);
    }
}

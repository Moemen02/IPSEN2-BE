package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.WasteDAO;
import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste")
public class ArticleControllerV2 {
    private final WasteDAO wasteDAO;
//    private final WasteInterface wasteInterface = null;
    private final ArticleInterface articleInterface;

    public ArticleControllerV2(WasteDAO wasteDAO, ArticleInterface articleInterface) {
        this.wasteDAO = wasteDAO;
        this.articleInterface = articleInterface;
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<List<ArticleV2>> getWaste(@PathVariable int pageNo,
                                                    @PathVariable int pageSize){
        return ResponseEntity.ok().body(this.articleInterface.getPagedWaste(pageNo, pageSize));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleV2> getWasteById(@PathVariable Long id) {
        try {
            ArticleV2 checkedWaste = this.wasteDAO.getWasteById(id);
            return new ResponseEntity<>(checkedWaste, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ArticleV2> addWaste (@RequestBody final ArticleV2 waste) throws ChangeSetPersister.NotFoundException {
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
    public ResponseEntity<ArticleV2> updateWaste(@PathVariable final Long id, @RequestBody final ArticleV2 waste) throws ChangeSetPersister.NotFoundException {
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

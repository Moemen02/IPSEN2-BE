package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleDescriptionDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleDescription;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/article_description")
public class ArticleDescriptionController {
    private final ArticleDescriptionDAO articleDescriptionDAO;

    public ArticleDescriptionController(ArticleDescriptionDAO articleDescriptionDAO) {
        this.articleDescriptionDAO = articleDescriptionDAO;
    }

    @GetMapping
    public ResponseEntity<List<ArticleDescription>> getAllWasteDescription() {
        return ResponseEntity.ok(
                this.articleDescriptionDAO.getAllWasteDescription()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleDescription> getWasteDescriptionById(@PathVariable Long id) {
        try {
            ArticleDescription checkedWasteDescription = this.articleDescriptionDAO.getWasteDescriptionById(id);
            return new ResponseEntity<>(checkedWasteDescription, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ArticleDescription> addWasteDescription(@RequestBody ArticleDescription wasteDescription) {
        if (this.articleDescriptionDAO.addWasteDescription(wasteDescription) == null) {
            throw new NullPointerException("WasteData is empty!");
        } else {
            return ResponseEntity.ok(wasteDescription);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ArticleDescription> updateWasteDescription(@PathVariable final Long id, @RequestBody final ArticleDescription wasteDescription) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        try {
            articleDescriptionDAO.getWasteDescriptionById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(
                articleDescriptionDAO.updateWasteDescription(id, wasteDescription)
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWasteDescription(@PathVariable final Long id) {
        try {
            articleDescriptionDAO.deleteWasteDescriptionById(id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }
}

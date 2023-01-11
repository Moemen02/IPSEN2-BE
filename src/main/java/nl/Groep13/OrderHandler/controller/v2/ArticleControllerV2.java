package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleDAOV2;
import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/article")
public class ArticleControllerV2 {
    private final ArticleDAOV2 articleDAOV2;
//    private final WasteInterface wasteInterface = null;
    private final ArticleInterface articleInterface;

    public ArticleControllerV2(ArticleDAOV2 wasteDAO, ArticleInterface articleInterface) {
        this.articleDAOV2 = wasteDAO;
        this.articleInterface = articleInterface;
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<List<ArticleV2>> getArticles(@PathVariable int pageNo,
                                                    @PathVariable int pageSize){
        return ResponseEntity.ok().body(this.articleInterface.getPagedWaste(pageNo, pageSize));
    }

    public Optional<ArticleV2> getSingleArticle(Long id){
        return this.articleInterface.getWasteById(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleV2> getWasteById(@PathVariable Long id) {
        ArticleV2 checkedWaste = this.articleDAOV2.getWasteById(id).get();
        return new ResponseEntity<>(checkedWaste, HttpStatus.FOUND);
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
            articleDAOV2.addArticle(waste);
            return ResponseEntity.ok(waste);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleV2> updateWaste(@PathVariable final Long id, @RequestBody final ArticleV2 waste) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        this.articleDAOV2.getWasteById(id);
        return ResponseEntity.ok(
                articleDAOV2.updateWaste(id, waste)
        );
    }
}

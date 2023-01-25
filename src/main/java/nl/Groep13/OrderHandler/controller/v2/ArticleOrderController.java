package nl.Groep13.OrderHandler.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import nl.Groep13.OrderHandler.service.V2.ArticleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v2/article_order")
public class ArticleOrderController {

    private final ArticleOrderService articleOrderService;


    Gson gson = new Gson();

    @Autowired

    public ArticleOrderController(ArticleOrderService articleOrderService, Gson gson) {
        this.articleOrderService = articleOrderService;
        this.gson = gson;
    }

    @GetMapping(value = "/{ID}")
    @ResponseBody
    public ResponseEntity<Optional<ArticleOrder>> getWasteOrderById(@PathVariable Long ID){
        try{
            Optional<ArticleOrder> articleOrder = this.articleOrderService.getArticleOrderById(ID);
            return new ResponseEntity<>(articleOrder, HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ArticleOrder>> getAllArticleOrders() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                this.articleOrderService.getAllArticleOrders()
        );
    }


    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public Optional<ArticleOrder> updateArticleOrder(@PathVariable Long id, @RequestBody Optional<ArticleOrder> articleOrder) throws ChangeSetPersister.NotFoundException {
        return this.articleOrderService.updateWasteOrder(id,  articleOrder);
    }
}

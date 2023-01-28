package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.controller.ArticleController;
import nl.Groep13.OrderHandler.model.v2.*;
import nl.Groep13.OrderHandler.service.ArticleService;
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
    private final WasteLocationController wasteLocationController;
    private final LocationControllerV2 locationControllerV2;
    private final CategoryLocationController categoryLocationController;
    private final ArticleController articleController;

    Gson gson = new Gson();

    @Autowired

    public ArticleOrderController(ArticleOrderService articleOrderService, ArticleService articleService, ArticleControllerV2 articleControllerV2, WasteLocationController wasteLocationController, LocationControllerV2 locationControllerV2, CategoryLocationController categoryLocationController, ArticleController articleController, Gson gson) {
        this.articleOrderService = articleOrderService;
        this.wasteLocationController = wasteLocationController;
        this.locationControllerV2 = locationControllerV2;
        this.categoryLocationController = categoryLocationController;
        this.articleController = articleController;
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

    @GetMapping("/{orderId}/article/location")
    public CategoryLocation getLocation(@PathVariable Long orderId) throws ChangeSetPersister.NotFoundException {
            ArticleV2 article = this.getWasteOrderById(orderId).getBody().get().getArticleID();
            ArticleLocation articleLocation = wasteLocationController.getArticleLocationByOrderId(article.getId());
            if (articleLocation.getLocationID() != null){
                Long locationV2 = locationControllerV2.getLocationByArticleLocation(articleLocation.getLocationID()).getCategory_locationID();
                return categoryLocationController.getCategoryLocationByLocation(locationV2);
            }
            return null;
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

    @PostMapping
    public ResponseEntity<ArticleOrder> addArticleOrder(@RequestBody ArticleOrder articleOrder) {
        if (this.articleOrderService.addArticleOrder(articleOrder) == null) {
            throw new NullPointerException("ArticleOrder is empty!");
        } else {
            return ResponseEntity.ok(articleOrder);
        }
    }
}

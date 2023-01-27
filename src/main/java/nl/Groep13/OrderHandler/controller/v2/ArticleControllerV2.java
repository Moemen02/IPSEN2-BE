package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleDAOV2;
import nl.Groep13.OrderHandler.controller.UserController;
import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import nl.Groep13.OrderHandler.record.ArticleCustomerRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import nl.Groep13.OrderHandler.utils.Pager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/article")
public class ArticleControllerV2 {
    private final ArticleDAOV2 articleDAOV2;
    private final ArticleInterface articleInterface;
    private final UsageController usageController;
    private final WasteLocationController wasteLocationController;
    private final ArticleOrderController articleOrderController;
    private final UserController userController;
    @Autowired
    private AuthenticationManager authManager;
    public ArticleControllerV2(ArticleDAOV2 wasteDAO, ArticleInterface articleInterface, WasteLocationController wasteLocationController, UsageController usageController, ArticleOrderController articleOrderController, UserController userController) {
        this.articleDAOV2 = wasteDAO;
        this.articleInterface = articleInterface;
        this.wasteLocationController = wasteLocationController;
        this.usageController = usageController;
        this.articleOrderController = articleOrderController;
        this.userController = userController;
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
    public ResponseEntity<ArticleV2> addWaste (@RequestBody final ArticleCustomerRec articleCustomerRec) throws ChangeSetPersister.NotFoundException {
        ArticleV2 articleV2 = new ArticleV2();
        ArticleOrder articleOrder = new ArticleOrder();
        CustomerV2 customer = new CustomerV2();
        articleV2.setArticle_dataID(articleCustomerRec._article()._article_dataID());
        articleV2.setArticle_descriptionID(articleCustomerRec._article()._article_descriptionID());
        articleV2.setUsageID(usageController.getUsageById(usageController.setUsageType(articleV2.getArticle_dataID())).getBody());
        ArticleV2 reArticle = articleDAOV2.addArticle(articleV2);
        wasteLocationController.createLoction(articleCustomerRec._customer().id(), reArticle);
        customer.setID(articleCustomerRec._customer().id());
        customer.setName(articleCustomerRec._customer().Name());
        customer.setAddressID(articleCustomerRec._customer().AddressID());
        articleOrder.setArticleID(reArticle);
        articleOrder.setCustomerID(customer);
        articleOrder.setFinished(false);
        articleOrderController.addArticleOrder(articleOrder);
        return ResponseEntity.ok(reArticle);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ArticleV2> updateWaste(@PathVariable final Long id, @RequestBody final ArticleV2 waste) throws ChangeSetPersister.NotFoundException, IllegalAccessException {
        this.articleDAOV2.getWasteById(id);
        return ResponseEntity.ok(
                articleDAOV2.updateWaste(id, waste)
        );
    }

    @GetMapping
    public ResponseEntity<List<ArticleV2>> getWaste(@RequestParam int page) {

        List<ArticleV2> fullList = this.articleDAOV2.getArticle();
        List<ArticleV2> toSend = Pager.getRequestedItems(fullList, page);

        return ResponseEntity.ok()
                .headers(Pager.addHeaders(fullList.size()))
                .body(toSend);
    }
}

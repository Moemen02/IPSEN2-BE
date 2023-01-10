package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/waste/location")
public class WasteLocationController {

    private final WasteLocationInterface wasteLocationInterface;
    private final CategoryLocationController categoryLocationController;
    private final UsageController usageController;
    private final CustomerControllerV2 customerController;

    public WasteLocationController(WasteLocationInterface wasteLocationInterface, CategoryLocationController categoryLocationController, UsageController usageController, CustomerControllerV2 customerController) {
        this.wasteLocationInterface = wasteLocationInterface;
        this.categoryLocationController = categoryLocationController;
        this.usageController = usageController;
        this.customerController = customerController;
    }

    @GetMapping
    public ResponseEntity<String> getWasteLocation(){
        return ResponseEntity.ok().body("dit moet een wastelocation item zijn");
    }


    public ArticleLocation createLoction(Long customerID, ArticleV2 article) throws ChangeSetPersister.NotFoundException {
        ArticleLocation articleLocation = new ArticleLocation();
        Long UsageID = article.getUsageID().getId();
        //TODO: maak check of de klant stof wilt hebben of niet
        CustomerV2 customer = this.customerController.getCustomer(customerID).get();


        //TODO: haal usage op om te weten wat er mee gedaan moet worden
        String usageType = usageController.getUsageType(article.getUsageID().getId());
        if (usageType.equals("RETOUR")){
            articleLocation = new ArticleLocation(0L, 0L, UsageID);
            //TODO: save to database
            return articleLocation;
        }
        else {
            List<Long> reqIdList = categoryLocationController.getCatLocIds();
            reqIdList.forEach(id -> {
                try {
                    checkWhatLocation(article.getArticle_dataID().getComposition(),categoryLocationController.getCatLocationRequirements(id));
                } catch (ChangeSetPersister.NotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        //TODO:
        return articleLocation;
    }

    public void checkWhatLocation(String composition, List<Requirement> requirementList){
        //TODO: get percentage and composition from composition and compare with value of requirement list
        //TODO: when done, get location when is comparison is good
        System.out.print(composition);
        System.out.print(requirementList);
    }
}

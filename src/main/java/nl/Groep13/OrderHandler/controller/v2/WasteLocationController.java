package nl.Groep13.OrderHandler.controller.v2;

import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/waste/location")
public class WasteLocationController {

    private final WasteLocationInterface wasteLocationInterface;
    private final UsageController usageController;
    private final CustomerControllerV2 customerController;

    public WasteLocationController(WasteLocationInterface wasteLocationInterface, UsageController usageController, CustomerControllerV2 customerController) {
        this.wasteLocationInterface = wasteLocationInterface;
        this.usageController = usageController;
        this.customerController = customerController;
    }

    @GetMapping
    public ResponseEntity<String> getWasteLocation(){
        return ResponseEntity.ok().body("dit moet een wastelocation item zijn");
    }

    public void createLoction(Long customerID, ArticleV2 article) throws ChangeSetPersister.NotFoundException {
        //TODO: maak check of de klant stof wilt hebben of niet
        CustomerV2 customer = this.customerController.getCustomer(customerID).get();



        //TODO: haal usage op om te weten wat er mee gedaan moet worden
        String usageType = usageController.getUsageType(article.getUsageID().getId());
        if (usageType.equals("RETOUR")){
            Long UsageID = article.getUsageID().getId();
            ArticleLocation articleLocation = new ArticleLocation(0L, 0L, UsageID);
        }

        //TODO:
    }
}

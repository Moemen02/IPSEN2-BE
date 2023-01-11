package nl.Groep13.OrderHandler.controller.v2;


import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleLocation;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import nl.Groep13.OrderHandler.model.v2.Requirement;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
        HashMap<String, Integer> listCompValue = new HashMap<>();
        HashMap<String, Integer> compValue = new HashMap<>();
        //TODO: get percentage and composition from composition and compare with value of requirement list
        //TODO: when done, get location when is comparison is good
        requirementList.forEach(requirement -> {
            String req = requirement.getRequirement().toLowerCase();
            String[] result = req.split(" ");

            if (req.equals("overig")){
//                System.out.println(requirement.getRequirement());
            } else{
                String resultPercentage = result[0].replace("%", "");
                Integer resultNum = Integer.parseInt(resultPercentage);
                if (result[1].equals("pes")) {
                    result[1] = "trevira";
                }
                listCompValue.put(result[1], resultNum);
            }
        });

        if (composition.contains("/")){
            int index = composition.indexOf("/");
            String toSplitValue = composition.substring(composition.lastIndexOf("/") +1);
            String[] theNewResult = toSplitValue.split(" ");
            System.out.println(theNewResult[2]);
            if (index != 1) {
                String req = composition.substring(0, index);
                String[] result = req.split(" ");
                String resultPercentage = result[0].replace("%", "");
                Integer resultNum = Integer.parseInt(resultPercentage);
                compValue.put(result[1].toLowerCase(), resultNum);
            }
        }

//        System.out.println(compValue + " comp");
//        System.out.println(listCompValue);
    }
}

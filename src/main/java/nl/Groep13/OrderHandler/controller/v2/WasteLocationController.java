package nl.Groep13.OrderHandler.controller.v2;


import nl.Groep13.OrderHandler.interfaces.WasteLocationInterface;
import nl.Groep13.OrderHandler.model.v2.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v2/waste/location")
public class WasteLocationController {

    private final WasteLocationInterface wasteLocationInterface;
    private final CategoryLocationController categoryLocationController;
    private final UsageController usageController;
    private final CustomerControllerV2 customerController;
    private final LocationControllerV2 locationController;
//    private HashMap<String, Integer> listCompValue = new HashMap<>();
    private HashMap<Long, HashMap<String, Integer>> listCompValue = new HashMap<>();
    private HashMap<String, Integer> compValue = new HashMap<>();

    public ArticleLocation getArticleLocationByOrderId(Long orderId){
        Optional<ArticleLocation> articleLocation = wasteLocationInterface.getArticleLocationByOrderId(orderId);
        if (articleLocation.isPresent()){
            return articleLocation.get();
        }
        return null;
    }


    public WasteLocationController(WasteLocationInterface wasteLocationInterface, CategoryLocationController categoryLocationController, UsageController usageController, CustomerControllerV2 customerController, LocationControllerV2 locationController) {
        this.wasteLocationInterface = wasteLocationInterface;
        this.categoryLocationController = categoryLocationController;
        this.usageController = usageController;
        this.customerController = customerController;
        this.locationController = locationController;
    }

    @GetMapping
    public ResponseEntity<String> getWasteLocation(){
        return ResponseEntity.ok().body("dit moet een wastelocation item zijn");
    }

    @GetMapping("/{id}")
    public Optional<ArticleLocation> getArticleLocationById(@PathVariable Long id){
        return this.wasteLocationInterface.getWasteLocationById(id);
    }


    public ArticleLocation createLoction(Long customerID, ArticleV2 article) throws ChangeSetPersister.NotFoundException {
        compValue = new HashMap<>();
        listCompValue = new HashMap<>();
        ArticleLocation articleLocation = new ArticleLocation();
        Long UsageID = article.getUsageID().getId();
        //TODO: maak check of de klant stof wilt hebben of niet
        CustomerV2 customer = this.customerController.getCustomer(customerID).get();


        //TODO: haal usage op om te weten wat er mee gedaan moet worden
        String usageType = usageController.getUsageType(article.getUsageID().getId());
        if (usageType.equals("RETOUR")){
            articleLocation = new ArticleLocation(0L, article.getId(), UsageID);
            //TODO: save to database
            return articleLocation;
        }
        else {
            List<Long> reqIdList = categoryLocationController.getCatLocIds();
            //foreach location there is I check if the article belongs there
            reqIdList.forEach(id -> {
                try {
                    checkWhatLocation(id, article.getArticle_dataID().getComposition(),categoryLocationController.getCatLocationRequirements(id));
                } catch (ChangeSetPersister.NotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            setLocation(compValue, article, UsageID);
        }

        //TODO:
        return articleLocation;
    }

    //TODO: make this code cleaner lol
    public void checkWhatLocation(Long requirementId, String composition, List<Requirement> requirementList){
        //TODO: get percentage and composition from composition and compare with value of requirement list
        //TODO: when done, get location when is comparison is good
        HashMap<String, Integer> requirementUsage = new HashMap<>();
        requirementList.forEach(requirement -> {
            String req = requirement.getRequirement().toLowerCase();
            String[] result = req.split(" ");
            if (!req.equals("overig")){
                Integer reqPerc = removePercentage(result[0]);
                requirementUsage.put(result[1], reqPerc);
                listCompValue.put(requirementId, requirementUsage);
            }
        });

        if (composition.contains("/")){
            int index = composition.indexOf("/");
            String toSplitValue = composition.substring(composition.lastIndexOf("/") +1);
            String[] theNewResult = toSplitValue.split(" ");
            String resultPercentage1 = theNewResult[1].replace("%", "");
            Integer firstResNmr= Integer.parseInt(resultPercentage1);
            compValue.put(theNewResult[2], firstResNmr);
            if (index != 1) {
                String req = composition.substring(0, index);
                String[] result = req.split(" ");
                String resultPercentage = result[0].replace("%", "");
                Integer resultNum = Integer.parseInt(resultPercentage);
                compValue.put(result[1].toLowerCase(), resultNum);
            }
        } else {
            String req = composition.toLowerCase();
            String[] comReq = req.split(" ");
            String noPersentage = comReq[0].replace("%", "");
            Integer noPersNmr= Integer.parseInt(noPersentage);
            compValue.put(comReq[1], noPersNmr);
        }

    }

    public Integer removePercentage(String stringToUpdate){
        String resultPercentage = stringToUpdate.replace("%", "");
        return Integer.parseInt(resultPercentage);
    }

    public void setLocation(HashMap<String, Integer> compValue, ArticleV2 article, Long UsageID){
        ArrayList<String> comValArr = new ArrayList<>(compValue.keySet());
        ArrayList<Integer> comValArrVal = new ArrayList<>(compValue.values());

        System.out.println(listCompValue);

        for (Map.Entry<Long, HashMap<String, Integer>> entry : listCompValue.entrySet()){
            if (entry.getKey().equals(1L)){

                savingArticleLocation(article, UsageID, comValArr, comValArrVal, entry);
            }
            else if (entry.getKey().equals(2L)){

                entry.getValue().keySet().forEach(value -> {
                    if (comValArr.contains(value)){
                        comValArrVal.forEach(comval -> {
                            if (comval >= 50 && comval <= 99){
                                LocationV2 newLocation = new LocationV2(entry.getKey());
                                LocationV2 savedLocation = locationController.saveLocation(newLocation);
                                ArticleLocation articleLocation = new ArticleLocation(savedLocation.getId(), article.getId(), UsageID);
                                wasteLocationInterface.addWasteLocation(articleLocation);
                            }
                        });
                    }
                });
            }

            else if (entry.getKey().equals(3L)){

                entry.getValue().keySet().forEach(value -> {
                    comValArrVal.forEach(comval -> {
                        if (comval >= 20 && comval <= 49){
                            LocationV2 newLocation = new LocationV2(entry.getKey());
                            LocationV2 savedLocation = locationController.saveLocation(newLocation);
                            ArticleLocation articleLocation = new ArticleLocation(savedLocation.getId(), article.getId(), UsageID);
                            wasteLocationInterface.addWasteLocation(articleLocation);
                        }
                    });
                });
            }

            else if (entry.getKey().equals(4L)){

                savingArticleLocation(article, UsageID, comValArr, comValArrVal, entry);
            }

            else if (entry.getKey().equals(5L)){

                savingArticleLocation(article, UsageID, comValArr, comValArrVal, entry);
            }

            else if (entry.getKey().equals(6L)){

                savingArticleLocation(article, UsageID, comValArr, comValArrVal, entry);
            }
        }
    }

    private void savingArticleLocation(ArticleV2 article, Long UsageID, ArrayList<String> comValArr, ArrayList<Integer> comValArrVal, Map.Entry<Long, HashMap<String, Integer>> entry) {
        entry.getValue().keySet().forEach(value -> {
            if (comValArr.contains(value)){
                comValArrVal.forEach(comval -> {
                    if (comval.equals(entry.getValue().get(value))) {
                        LocationV2 newLocation = new LocationV2(entry.getKey());
                        LocationV2 savedLocation = locationController.saveLocation(newLocation);
                        ArticleLocation articleLocation = new ArticleLocation(savedLocation.getId(), article.getId(), UsageID);
                        wasteLocationInterface.addWasteLocation(articleLocation);
                    }
                });
            }
        });
    }
}

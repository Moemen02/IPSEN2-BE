package nl.Groep13.OrderHandler.service.V2;


import nl.Groep13.OrderHandler.controller.v2.*;
import nl.Groep13.OrderHandler.interfaces.LabelInterface;

import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.model.v2.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


@Service
public class LabelDataServiceV2 {

    LabelInterface labelInterface;
    LabelControllerV2 labelControllerV2;
    ArticleOrderController articleOrderController;
    LocationControllerV2 locationControllerV2;
    CustomerControllerV2 customerControllerV2;
    AddressController addressController;
    WasteController wasteController;
    ArticleOrderService articleOrderService;

    public LabelDataServiceV2(LabelInterface labelInterface, ArticleOrderController articleOrderController, LocationControllerV2 locationControllerV2, CustomerControllerV2 customerControllerV2, AddressController addressController, WasteController wasteController, ArticleOrderService articleOrderService) {
        this.labelInterface = labelInterface;
        this.articleOrderController = articleOrderController;
        this.locationControllerV2 = locationControllerV2;
        this.customerControllerV2 = customerControllerV2;
        this.addressController = addressController;
        this.wasteController = wasteController;
        this.articleOrderService = articleOrderService;
    }

    public HashMap<String, String> getLabelData(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<LabelV2> label = labelInterface.getLabelById(id);
        Optional<ArticleOrder> order = articleOrderController.getWasteOrderById(label.get().getId()).getBody();
        Optional<CustomerV2> customer = customerControllerV2.getCustomerById(order.get().getCustomerID());
        Optional<Address> adres = addressController.getAddressById(customer.get().getAddressID()).getBody();
        Optional<Waste> article = Optional.ofNullable(wasteController.getWasteById(order.get().getId()).getBody());
//      Optional<WasteLocation> articleLocation = wasteController.getArticleLocationById(article.get().getArticleLocationID()).getBody();

        HashMap<String, String> labelData = new HashMap<>();

        if (article.get().getUsageID().getId() == 1){
            labelData.put("vervoerder", "HollandHaag BV");
            labelData.put("klant", customer.get().getName());
            labelData.put("factuurklant", customer.get().getName());
            labelData.put("straat", adres.get().getStreetname() + " " + adres.get().getHousenumber());
            labelData.put("postcode", adres.get().getPostal_code());
            labelData.put("orderCode", article.get().getArticle_dataID().getEancode());
            labelData.put("description", article.get().getArticle_descriptionID().getDescription());
            labelData.put("kleur", article.get().getArticle_dataID().getColor());
            labelData.put("metrage", "length: " + article.get().getArticle_dataID().getPatternLength() + "  width: " + article.get().getArticle_dataID().getPatternWidth());
            labelData.put("retour", "true");
            return labelData;
        }
        if(article.get().getUsageID().getId() == 2){
            labelData.put("klant", customer.get().getName());
            labelData.put("orderCode", article.get().getArticle_dataID().getEancode());
            labelData.put("description", article.get().getArticle_descriptionID().getDescription());
            labelData.put("kleur", article.get().getArticle_dataID().getColor());
            labelData.put("metrage", "length: " + article.get().getArticle_dataID().getPatternLength() + "  width: " + article.get().getArticle_dataID().getPatternWidth());
            labelData.put("retour", "false");
            return labelData;
        }
        return null;
    }


}

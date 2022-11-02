package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.controller.*;
import nl.Groep13.OrderHandler.model.*;
import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Service
public class MakeExcelService {

    LabelService labelService = new LabelService();
    OrderController orderController = new OrderController();
    LocationController locationController = new LocationController();
    CustomerController customerController = new CustomerController();
    AdressController adressController = new AdressController();
    ArticleController articleController = new ArticleController();


    private final String retourLabel = "src/main/resources/Labels/retourLabel.xlsx";

    public HashMap<String, String> getLabelData(Long id) throws ChangeSetPersister.NotFoundException, FileNotFoundException {
        Optional<Label> label = labelService.getLabel(id);
        Optional<lOrder> order = orderController.getOrderById(label.get().getOrderid());
        Optional<Location> location = locationController.getLocationByArticlenumber(label.get().getLocationid());
        Optional<Customer> customer = customerController.getCustomerById((long) order.get().getCustomerid());
        Optional<Adress> adress = adressController.getAdress(customer.get().getAddressid());
        Optional<Article> article = articleController.getArticle((long) order.get().getArticlenumber());
        Optional<ArticlePrice> articlePrice = articleController.getArticleById(article.get().getPriceid());

        HashMap<String, String> labelData = new HashMap<>();
        labelData.put("vervoerder", "HollandHaag BV");
        labelData.put("klant", customer.get().getName());
        labelData.put("klant", customer.get().getName());
        labelData.put("straat", adress.get().getStreetname() + " " + adress.get().getHousenumber());
        labelData.put("postcode", adress.get().getPostalcode());
//        labelData.put("Land", article.get().getLocation());
        labelData.put("orderCode", article.get().getEancode());
        labelData.put("description", articlePrice.get().getDescription());
        labelData.put("kleur", article.get().getColor());
        labelData.put("metrage","length: " + articlePrice.get().getPtrLength() + "  width: " + articlePrice.get().getPtrWidth());
        return labelData;

//        if (customer.get().isRetour_fabric()){
//            try {
//                FileInputStream labelTemplate = new FileInputStream(new File(retourLabel));
//                XSSFWorkbook retourLabel = new XSSFWorkbook(labelTemplate);
//
//                Sheet retourInpakLabel = retourLabel.getSheet("Inpaklabel");
//
//                Row row = retourInpakLabel.createRow(8);
//                Cell cell = row.createCell(3);
//                cell.setCellValue("Holland Haag BV");
//
//
//            }
//            catch (IOException e){
//
//            }

//        }



    }
}

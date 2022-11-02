package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.controller.CustomerController;
import nl.Groep13.OrderHandler.controller.LocationController;
import nl.Groep13.OrderHandler.controller.OrderController;
import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.model.Label;
import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.model.lOrder;
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
    CustomerController customerController = new CustomerController  ();

    private final String retourLabel = "src/main/resources/Labels/retourLabel.xlsx";

    public void toExcel(Long id) throws ChangeSetPersister.NotFoundException, FileNotFoundException {
        Optional<Label> label = labelService.getLabel(id);
        Optional<lOrder> order = orderController.getOrderById(label.get().getOrderid());
        Optional<Location> location = locationController.getLocationByArticlenumber(label.get().getLocationid());
        Optional<Customer> customer = customerController.getCustomerById((long) order.get().getCustomerid());

        HashMap<String, String> labelData = new HashMap<>();
        labelData.put("vervoerder", "HollandHaag BV");
        labelData.put("klant", customer.get().getName());
        labelData.put("klant", customer.get().getName());
//        labelData.put("adres", loca)

        if (customer.get().isRetour_fabric()){
            try {
                FileInputStream labelTemplate = new FileInputStream(new File(retourLabel));
                XSSFWorkbook retourLabel = new XSSFWorkbook(labelTemplate);

                Sheet retourInpakLabel = retourLabel.getSheet("Inpaklabel");

                Row row = retourInpakLabel.createRow(8);
                Cell cell = row.createCell(3);
                cell.setCellValue("Holland Haag BV");


            }
            catch (IOException e){

            }

        }


//        XSSFWorkbook workbook = new XSSFWorkbook();

    }
}

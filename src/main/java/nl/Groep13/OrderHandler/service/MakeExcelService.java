package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.controller.CustomerController;
import nl.Groep13.OrderHandler.controller.LocationController;
import nl.Groep13.OrderHandler.controller.OrderController;
import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.model.Label;
import nl.Groep13.OrderHandler.model.Location;
import nl.Groep13.OrderHandler.model.lOrder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeExcelService {

    LabelService labelService = new LabelService();
    OrderController orderController = new OrderController();
    LocationController locationController = new LocationController();
    CustomerController customerController = new CustomerController();

    public void toExcel(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Label> label = labelService.getLabel(id);
        Optional<lOrder> order = orderController.getOrderById(label.get().getOrderid());
        Optional<Location> location = locationController.getLocationByID(label.get().getLocationid());
        Optional<Customer> customer = customerController.getCustomerById((long) order.get().getCustomerid());

//        if (customer.get()

//        XSSFWorkbook workbook = new XSSFWorkbook();

    }
}

package nl.Groep13.OrderHandler.controller.v2;

import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import nl.Groep13.OrderHandler.service.V2.CustomerServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v2/customer")
public class CustomerControllerV2 {



    private CustomerServiceV2 customerService;

    Gson gson = new Gson();

    @Autowired
    public CustomerControllerV2(CustomerServiceV2 customerService){
        this.customerService = customerService;
    }

    /**
     * This function gets all the customers from the DB
     * This data will be used in the front-end application
     * @returns a list filled with all customers
     */
    @GetMapping
    public ResponseEntity<List<CustomerV2>> getAllCustomers() {
        return ResponseEntity.ok(
                this.customerService.getAllCustomers()
        );
    }

    /**
     * this function gets one customer that matches the input id
     * you give the id as input in the link
     * @param id - this is the input id that you give to the link
     * @returns a customer that matches the input id
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<CustomerV2> getCustomerById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException{
        return this.customerService.getCustomerById(id);
    }

    public Optional<CustomerV2> getCustomer(Long id) throws ChangeSetPersister.NotFoundException{
        return this.customerService.getCustomerById(id);
    }

    /**
     * this function updates the customer that matches the input id
     * @param  - this is the input id that you give to the link
     * @param customer - this is a customer
     * @returns a updated model of customer
     */
//    @PutMapping(value = "/{id}")
//    @ResponseBody
//    public Optional<CustomerV2> updateCustomer(@PathVariable Long id, @RequestBody Map<String, String> customer){
//        String customerToJson = gson.toJson(customer);
//        CustomerV2 newCustomer = gson.fromJson(customerToJson, CustomerV2.class);
//
//        return this.customerService.updateCustomer(id, Optional.of(newCustomer));
//
//    }

//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteCustomer(@PathVariable final Long id) {
//        try {
//            customerService.deleteCustomer(id);
//        } catch (ChangeSetPersister.NotFoundException e) {
//            return ResponseEntity.ok(false);
//        }
//        return ResponseEntity.ok(true);
//    }

    @PostMapping
    public ResponseEntity<Boolean> addCustomer(@RequestParam Map<String, String> customer) {
        String customerToJson = gson.toJson(customer);
        CustomerV2 newCustomer = gson.fromJson(customerToJson, CustomerV2.class);
        if (this.customerService.addCustomer(newCustomer) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}

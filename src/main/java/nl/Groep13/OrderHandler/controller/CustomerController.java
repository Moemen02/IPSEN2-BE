package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.exception.NoCustomerFoundGivenId;
import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {


    private static Customer customer;
    private final CustomerService customerService;
    private ObjectMapper objectMapper;
    Gson gson = new Gson();

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers(){
        List<Customer> customers = this.customerService.getAllCustomers();

        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = this.customerService.getCustomerById(id);

        return customer;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
//        String customerToJson = gson.toJson(customer);
//        Customer newCustomer = gson.fromJson(customerToJson, Customer.class);
        try {
            return new ResponseEntity<> ( this.customerService.updateCustomer(id, customer), HttpStatus.OK);
        } catch (NoCustomerFoundGivenId e) {
            return new ResponseEntity<> ( e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable final Long id) {
        try {
            customerService.deleteCustomer((Long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Boolean> addCustomer(@RequestParam Map<String, String> customer) {
        String customerToJson = gson.toJson(customer);
        Customer newCustomer = gson.fromJson(customerToJson, Customer.class);
        if (this.customerService.addCustomer(newCustomer) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }
}

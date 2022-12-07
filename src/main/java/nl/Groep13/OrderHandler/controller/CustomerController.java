package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {



    private CustomerService customerService;

    Gson gson = new Gson();

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    /**
     * This function gets all the customers from the DB
     * This data will be used in the front-end application
     * @returns a list filled with all customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
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
    public Optional<Customer> getCustomerById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException{
        return this.customerService.getCustomerById(id);
    }

    /**
     * this function updates the customer that matches the input id
     * @param id - this is the input id that you give to the link
     * @param customer - this is a customer
     * @returns a updated model of customer
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    public Optional<Customer> updateCustomer(@PathVariable Long id, @RequestBody Map<String, String> customer){
        String customerToJson = gson.toJson(customer);
        Customer newCustomer = gson.fromJson(customerToJson, Customer.class);

        return this.customerService.updateCustomer(id, Optional.of(newCustomer));

    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable final Long id) {
        try {
            customerService.deleteCustomer(id);
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

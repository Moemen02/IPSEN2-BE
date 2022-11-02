package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.CustomerDAO;
import nl.Groep13.OrderHandler.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private CustomerDAO customerDAO;
    private Customer customer;

    @Autowired
    public CustomerController(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public CustomerController(){}

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers(){
        List<Customer> customers = this.customerDAO.getAllCustomers();

        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = this.customerDAO.getCustomerById(id);

        return customer;
    }
}

package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.CustomerDAO;
import nl.Groep13.OrderHandler.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private CustomerDAO customerDAO;
    private Customer customer;

    @Autowired
    public CustomerController(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers(){
        List<Customer> customers = this.customerDAO.getAllCustomers();

        return customers;
    }
}

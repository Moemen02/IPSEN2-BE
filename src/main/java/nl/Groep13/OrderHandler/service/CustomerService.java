package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.CustomerDAO;
import nl.Groep13.OrderHandler.exception.NoCustomerFoundGivenId;
import nl.Groep13.OrderHandler.model.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService( CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return this.customerDAO.getCustomerById(id);
    }

    public Optional<Customer> updateCustomer(Long id, Optional<Customer> customer) {
        return this.customerDAO.updateCustomer(id, customer);

    }

    public void deleteCustomer(Long id) throws ChangeSetPersister.NotFoundException{
        this.customerDAO.deleteCustomer(id);
    }

    public Customer addCustomer( Customer customer){
        return this.customerDAO.addCustomer(customer);
    }
}

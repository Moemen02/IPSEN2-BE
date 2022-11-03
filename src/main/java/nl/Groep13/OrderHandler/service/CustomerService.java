package nl.Groep13.OrderHandler.service;

import nl.Groep13.OrderHandler.DAO.CustomerDAO;
import nl.Groep13.OrderHandler.model.Customer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(final CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    public Optional<Customer> getCustomerById(final Long id) throws ChangeSetPersister.NotFoundException {
        return this.customerDAO.getCustomerById(id);
    }

    public Optional<Customer> updateCustomer(Long id, final Optional<Customer> customer) {
        return this.customerDAO.updateCustomer(id, customer);

    }

    public void deleteCustomer(final Long id) throws ChangeSetPersister.NotFoundException{
        this.customerDAO.deleteCustomer(id);
    }

    public Customer addCustomer(final Customer customer){
        return this.customerDAO.addCustomer(customer);
    }
}

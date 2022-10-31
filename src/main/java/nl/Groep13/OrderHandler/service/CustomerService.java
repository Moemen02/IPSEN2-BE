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

    private final CustomerDAO customerDAO;

    public CustomerService(final CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return this.customerDAO.getCustomerById(id);
    }

    public Customer updateCustomer(Long id, Customer customer) throws NoCustomerFoundGivenId {
        return this.customerDAO.updateCustomer(id, customer);
    }

    public void deleteCustomer(final Long id) throws ChangeSetPersister.NotFoundException{
        this.customerDAO.deleteCustomer(id);
    }

    public Customer addCustomer(final Customer customer){
        return this.customerDAO.addCustomer(customer);
    }
}

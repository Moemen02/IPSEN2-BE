package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {

    private final CustomerRepository customerRepository;

    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = this.customerRepository.findAll();

        return customers;
    }

    public Optional<Customer> getCustomerById(Long id){
        Optional<Customer> customer = this.customerRepository.findById(id);

        return customer;
    }
}
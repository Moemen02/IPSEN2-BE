package nl.Groep13.OrderHandler.DAO;

import nl.Groep13.OrderHandler.exception.NoCustomerFoundGivenId;
import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {

    private CustomerRepository customerRepository;

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

    public Optional<Customer> updateCustomer(Long id, Optional<Customer> customer) {
        System.out.println(id );
        System.out.println(customer);
        Optional<Customer> oldCustomerById = customerRepository.findById(id);
        Customer oldCustomer = oldCustomerById.get();
        Customer newCustomer = customer.get();

        newCustomer.setId((newCustomer.getId() == null) ? oldCustomer.getId() : newCustomer.getId());
        newCustomer.setAddressid((newCustomer.getAddressid() == null) ? oldCustomer.getAddressid() : newCustomer.getAddressid());
        newCustomer.setArticlenumber((newCustomer.getArticlenumber() == 0) ? oldCustomer.getArticlenumber() : newCustomer.getArticlenumber());
        newCustomer.setName((newCustomer.getName() == null) ? oldCustomer.getName() : newCustomer.getName());

        customerRepository.setCustomerById(
                newCustomer.getId(),
                newCustomer.getArticlenumber(),
                newCustomer.getAddressid(),
                newCustomer.getName(),
                newCustomer.isPerserved_fabric(),
                newCustomer.isRetour_fabric()
        );

        customerRepository.save(newCustomer);
        return customer;

    }

    public void deleteCustomer( Long id) throws ChangeSetPersister.NotFoundException{
        if (customerRepository.findById(id).isPresent()){
            customerRepository.deleteCustomerById(id);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Customer addCustomer( Customer customer){
        customer.setId(null);
        return this.customerRepository.save(customer);
    }
}

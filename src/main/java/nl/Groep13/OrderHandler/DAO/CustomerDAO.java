package nl.Groep13.OrderHandler.DAO;


import nl.Groep13.OrderHandler.model.Customer;
import nl.Groep13.OrderHandler.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO {

    private final CustomerRepository customerRepository;

    public CustomerDAO(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(final Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

    public Optional<Customer> updateCustomer(Long id, Optional<Customer> customer) {
        Optional<Customer> oldCustomerById = customerRepository.findById(id);
        Customer oldCustomer = oldCustomerById.get();
        Customer newCustomer = customer.get();

        newCustomer.setId((newCustomer.getId() == null) ? oldCustomer.getId() : newCustomer.getId());
        newCustomer.setAddressid((newCustomer.getAddressid() == null) ? oldCustomer.getAddressid() : newCustomer.getAddressid());
        newCustomer.setName((newCustomer.getName() == null) ? oldCustomer.getName() : newCustomer.getName());
        newCustomer.setPerserved_fabric((oldCustomer.isPerserved_fabric() == newCustomer.isPerserved_fabric()) ? oldCustomer.isPerserved_fabric() : newCustomer.isPerserved_fabric());
        newCustomer.setRetour_fabric((oldCustomer.isRetour_fabric() == newCustomer.isRetour_fabric()) ? oldCustomer.isRetour_fabric() : newCustomer.isRetour_fabric());

        customerRepository.setCustomerById(
                newCustomer.getAddressid(),
                newCustomer.getName(),
                newCustomer.isPerserved_fabric(),
                newCustomer.isRetour_fabric(),
                newCustomer.getId()
        );

        customerRepository.save(newCustomer);
        return customer;

    }

    public void deleteCustomer(final Long id) throws ChangeSetPersister.NotFoundException{
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

package nl.Groep13.OrderHandler.DAO.v2;


import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import nl.Groep13.OrderHandler.repository.v2.CustomerRepositoryV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAOV2 {

    private final CustomerRepositoryV2 customerRepository;

    public CustomerDAOV2(final CustomerRepositoryV2 customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerV2> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Optional<CustomerV2> getCustomerById(final Long id) throws ChangeSetPersister.NotFoundException {
        Optional<CustomerV2> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

//    public Optional<CustomerV2> updateCustomer(Long id, Optional<CustomerV2> customer) {
//        Optional<CustomerV2> oldCustomerById = customerRepository.findById(id);
//        CustomerV2 oldCustomer = oldCustomerById.get();
//        CustomerV2 newCustomer = customer.get();
//
//        newCustomer.setID((newCustomer.getID() == null) ? oldCustomer.getID() : newCustomer.getID());
//        newCustomer.setAddressID((newCustomer.getAddressID() == null) ? oldCustomer.getAddressID() : newCustomer.getAddressID());
//        newCustomer.setName((newCustomer.getName() == null) ? oldCustomer.getName() : newCustomer.getName());
//
//        customerRepository.setCustomerById(
//                newCustomer.getAddressID(),
//                newCustomer.getName(),
//                newCustomer.getID()
//        );
//
//        customerRepository.save(newCustomer);
//        return customer;
//
//    }
//
//    public void deleteCustomer(final Long id) throws ChangeSetPersister.NotFoundException{
//        if (customerRepository.findById(id).isPresent()){
//            customerRepository.deleteCustomerById(id);
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }

    public CustomerV2 addCustomer( CustomerV2 customer){
        return this.customerRepository.save(customer);
    }
}

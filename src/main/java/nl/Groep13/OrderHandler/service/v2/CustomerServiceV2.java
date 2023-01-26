package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.CustomerDAOV2;
import nl.Groep13.OrderHandler.model.v2.CustomerV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceV2 {

    private final CustomerDAOV2 customerDAO;

    public CustomerServiceV2(final CustomerDAOV2 customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<CustomerV2> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    public Optional<CustomerV2> getCustomerById(final Long id) throws ChangeSetPersister.NotFoundException {
        return this.customerDAO.getCustomerById(id);
    }

//    public Optional<CustomerV2> updateCustomer(Long id, final Optional<CustomerV2> customer) {
//        return this.customerDAO.updateCustomer(id, customer);
//
//    }

//    public void deleteCustomer(final Long id) throws ChangeSetPersister.NotFoundException{
//        this.customerDAO.deleteCustomer(id);
//    }

    public CustomerV2 addCustomer(final CustomerV2 customer){
        return this.customerDAO.addCustomer(customer);
    }
}

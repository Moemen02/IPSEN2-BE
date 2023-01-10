package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.WasteOrder;
import nl.Groep13.OrderHandler.repository.v2.WasteOrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WasteOrderDAO {

    private final WasteOrderRepository wasteOrderRepository;

    public WasteOrderDAO(WasteOrderRepository wasteOrderRepository) {
        this.wasteOrderRepository = wasteOrderRepository;
    }

    public List<WasteOrder> getAllWasteOrders(){
        return wasteOrderRepository.findAll();
    }

    public Optional<WasteOrder> getWasteOrderById(final Long ID) throws ChangeSetPersister.NotFoundException{
        Optional<WasteOrder> wasteOrder = wasteOrderRepository.findById(ID);
        if (wasteOrder.isPresent()) {
            return wasteOrder;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

//    public Optional<WasteOrder> updateWasteOrder(Long ID, Optional<WasteOrder> wasteOrder) throws ChangeSetPersister.NotFoundException{
//        Optional<WasteOrder> oldWasteOrderById = wasteOrderRepository.findById(ID);
//        if (oldWasteOrderById.isPresent()){
//            WasteOrder oldWasteOrder = oldWasteOrderById.get();
//            WasteOrder newWasteOrder = wasteOrder.get();
//
//            newWasteOrder.setID((newWasteOrder.getID() == null) ? oldWasteOrder.getID() : newWasteOrder.getID());
//            newWasteOrder.setWasteID((newWasteOrder.getWasteID() == null) ? oldWasteOrder.getWasteID() : newWasteOrder.getWasteID());
//            newWasteOrder.setCustomerID((newWasteOrder.getCustomerID() == null) ? oldWasteOrder.getCustomerID() : newWasteOrder.getCustomerID());
//            newWasteOrder.setFinished((oldWasteOrder.isFinished() == newWasteOrder.isFinished()) ? oldWasteOrder.isFinished() : newWasteOrder.isFinished());
//
//            wasteOrderRepository.setWasteOrderById(
//                    newWasteOrder.getCustomerID(),
//                    newWasteOrder.getWasteID(),
//                    newWasteOrder.isFinished(),
//                    newWasteOrder.getID()
//            );
//            wasteOrderRepository.save(newWasteOrder);
//            return wasteOrder;
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }

//    public void deleteOrderWaste(final Long ID) throws ChangeSetPersister.NotFoundException{
//        if (wasteOrderRepository.findById(ID).isPresent()){
//            wasteOrderRepository.deleteWasteOrdersByID(ID);
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }
}

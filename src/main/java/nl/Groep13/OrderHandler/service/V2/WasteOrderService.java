package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.WasteOrderDAO;
import nl.Groep13.OrderHandler.model.v2.WasteOrder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteOrderService {

    private final WasteOrderDAO wasteOrderDAO;

    public WasteOrderService(WasteOrderDAO wasteOrderDAO) {
        this.wasteOrderDAO = wasteOrderDAO;
    }

    public List<WasteOrder> getAllWasteOrders() throws ChangeSetPersister.NotFoundException{
        return this.wasteOrderDAO.getAllWasteOrders();
    }

    public Optional<WasteOrder> getWasteOrderById(Long ID) throws ChangeSetPersister.NotFoundException{
        return this.wasteOrderDAO.getWasteOrderById(ID);
    }

    public Optional<WasteOrder> updateWasteOrder(Long ID, Optional<WasteOrder> wasteOrder) throws ChangeSetPersister.NotFoundException{
        return this.wasteOrderDAO.updateWasteOrder(ID, wasteOrder);
    }
}

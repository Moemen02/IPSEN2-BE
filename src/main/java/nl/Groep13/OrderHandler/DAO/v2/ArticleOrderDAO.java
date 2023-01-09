package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import nl.Groep13.OrderHandler.repository.v2.ArticleOrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleOrderDAO {

    private final ArticleOrderRepository articleOrderRepository;

    public ArticleOrderDAO(ArticleOrderRepository articleOrderRepository) {
        this.articleOrderRepository = articleOrderRepository;
    }

    public List<ArticleOrder> getAllArticleOrders(){
        return articleOrderRepository.findAll();
    }

    public Optional<ArticleOrder> getArticleOrderById(final Long ID) throws ChangeSetPersister.NotFoundException{
        Optional<ArticleOrder> articleOrder = articleOrderRepository.findById(ID);
        if (articleOrder.isPresent()) {
            return articleOrder;
        }
        throw new ChangeSetPersister.NotFoundException();
    }

//    public Optional<ArticleOrder> updateWasteOrder(Long ID, Optional<ArticleOrder> wasteOrder) throws ChangeSetPersister.NotFoundException{
//        Optional<ArticleOrder> oldWasteOrderById = articleOrderRepository.findById(ID);
//        if (oldWasteOrderById.isPresent()){
//            ArticleOrder oldWasteOrder = oldWasteOrderById.get();
//            ArticleOrder newWasteOrder = wasteOrder.get();
//
//            newWasteOrder.setID((newWasteOrder.getID() == null) ? oldWasteOrder.getID() : newWasteOrder.getID());
//            newWasteOrder.setWasteID((newWasteOrder.getWasteID() == null) ? oldWasteOrder.getWasteID() : newWasteOrder.getWasteID());
//            newWasteOrder.setCustomerID((newWasteOrder.getCustomerID() == null) ? oldWasteOrder.getCustomerID() : newWasteOrder.getCustomerID());
//            newWasteOrder.setFinished((oldWasteOrder.isFinished() == newWasteOrder.isFinished()) ? oldWasteOrder.isFinished() : newWasteOrder.isFinished());
//
//            articleOrderRepository.setWasteOrderById(
//                    newWasteOrder.getCustomerID(),
//                    newWasteOrder.getWasteID(),
//                    newWasteOrder.isFinished(),
//                    newWasteOrder.getID()
//            );
//            articleOrderRepository.save(newWasteOrder);
//            return wasteOrder;
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }

//    public void deleteOrderWaste(final Long ID) throws ChangeSetPersister.NotFoundException{
//        if (articleOrderRepository.findById(ID).isPresent()){
//            articleOrderRepository.deleteWasteOrdersByID(ID);
//        }
//        throw new ChangeSetPersister.NotFoundException();
//    }
}

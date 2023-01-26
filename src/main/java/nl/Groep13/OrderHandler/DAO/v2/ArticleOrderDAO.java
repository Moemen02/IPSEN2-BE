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

    public Optional<ArticleOrder> updateWasteOrder(Long ID, Optional<ArticleOrder> articleOrder) throws ChangeSetPersister.NotFoundException{
        Optional<ArticleOrder> oldWasteOrderById = articleOrderRepository.findById(ID);
        if (oldWasteOrderById.isPresent()){
            ArticleOrder newWasteOrder = articleOrder.get();
            ArticleOrder oldWasteOrder = oldWasteOrderById.get();

            newWasteOrder.setArticleID(oldWasteOrderById.get().getArticleID());
            newWasteOrder.setCustomerID(oldWasteOrderById.get().getCustomerID());
            newWasteOrder.setFinished(true);
            newWasteOrder.setId(oldWasteOrderById.get().getId());

            articleOrderRepository.setArticleOrderById(
                    newWasteOrder.getCustomerID(),
                    newWasteOrder.getArticleID(),
                    newWasteOrder.isFinished(),
                    newWasteOrder.getId()
            );
            articleOrderRepository.save(newWasteOrder);
            return Optional.of(newWasteOrder);
        }
        throw new ChangeSetPersister.NotFoundException();
    }

}

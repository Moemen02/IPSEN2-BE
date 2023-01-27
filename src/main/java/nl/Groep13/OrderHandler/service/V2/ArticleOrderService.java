package nl.Groep13.OrderHandler.service.V2;

import nl.Groep13.OrderHandler.DAO.v2.ArticleOrderDAO;
import nl.Groep13.OrderHandler.model.v2.ArticleOrder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleOrderService {

    private final ArticleOrderDAO articleOrderDAO;

    public ArticleOrderService(ArticleOrderDAO articleOrderDAO) {
        this.articleOrderDAO = articleOrderDAO;
    }

    public List<ArticleOrder> getAllArticleOrders() throws ChangeSetPersister.NotFoundException{
        return this.articleOrderDAO.getAllArticleOrders();
    }

    public Optional<ArticleOrder> getArticleOrderById(Long ID) throws ChangeSetPersister.NotFoundException{
        return this.articleOrderDAO.getArticleOrderById(ID);
    }

    public Optional<ArticleOrder> updateWasteOrder(Long ID, Optional<ArticleOrder> articleOrder) throws ChangeSetPersister.NotFoundException{
        return this.articleOrderDAO.updateWasteOrder(ID, articleOrder);
    }

    public ArticleOrder addArticleOrder(final ArticleOrder articleOrder) {
        return articleOrderDAO.addArticleOrder(articleOrder);
    }
}

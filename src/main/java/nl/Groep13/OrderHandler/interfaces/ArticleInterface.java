package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ArticleInterface {
//    List<ArticleV2> getWaste();
    List<ArticleV2> getPagedWaste(int pageNo, int pageSize);
    ArticleV2 getWasteById(Long id) throws ChangeSetPersister.NotFoundException;
    ArticleV2 updateWaste(Long id, ArticleV2 newWasteData) throws ChangeSetPersister.NotFoundException;
}

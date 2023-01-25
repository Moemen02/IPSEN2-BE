package nl.Groep13.OrderHandler.interfaces;

import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ArticleInterface {
//    List<ArticleV2> getWaste();
    List<ArticleV2> getPagedWaste(int pageNo, int pageSize);
    Optional<ArticleV2> getWasteById(Long id);
    ArticleV2 updateWaste(Long id, ArticleV2 newWasteData) throws ChangeSetPersister.NotFoundException, IllegalAccessException;
}

package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.interfaces.ArticleInterface;
import nl.Groep13.OrderHandler.model.v2.ArticleV2;
import nl.Groep13.OrderHandler.repository.v2.ArticleRepositoryV2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Component
public class ArticleDAOV2 implements ArticleInterface {

    private final ArticleRepositoryV2 articleRepositoryV2;

    public ArticleDAOV2(ArticleRepositoryV2 articleRepositoryV2) {
        this.articleRepositoryV2 = articleRepositoryV2;
    }

    @Override
    public List<ArticleV2> getPagedWaste(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ArticleV2> pagedResult = articleRepositoryV2.findAll(paging);
        return pagedResult.toList();
    }

    @Override
    public ArticleV2 getWasteById(Long id) throws ChangeSetPersister.NotFoundException {
        return null;
    }

    @Override
    public ArticleV2 updateWaste(Long id, ArticleV2 newWasteData) throws ChangeSetPersister.NotFoundException {
        return null;
    }
}

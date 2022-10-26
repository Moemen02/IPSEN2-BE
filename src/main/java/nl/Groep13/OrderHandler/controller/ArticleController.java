package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import nl.Groep13.OrderHandler.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private static Article article;
    private static ArticlePrice articlePrice;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(
                this.articleService.getAllArticles()
        );
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    @ResponseBody
    public ArticlePrice getAllArticlePrices(){
        //TODO echte code uit databae ophalen
        return null;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Article> getArticle(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return this.articleService.getArticle(id);
    }
}

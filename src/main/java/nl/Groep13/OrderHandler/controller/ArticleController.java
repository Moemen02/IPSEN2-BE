package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.DAO.ArticleDAO;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    private final ArticleDAO articleDAO;
    private static Article article;
    private static ArticlePrice articlePrice;

    @Autowired
    public ArticleController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Article Article(){
        //TODO echte code uit databae ophalen

        return null;
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    @ResponseBody
    public ArticlePrice getAllArticlePrices(){
        //TODO echte code uit databae ophalen
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Article> getArticle(@PathVariable long id){
        Optional<Article> articleById = this.articleDAO.getArticle(id);
        return articleById;
    }
}

package nl.Groep13.OrderHandler.controller;

import nl.Groep13.OrderHandler.controller.DAO.ArticleDAO;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    private final ArticleDAO articleDAO;
    private static Article article;
    private static ArticlePrice articlePrice;

    public ArticleController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object Article(){
        //TODO echte code uit databae ophalen
        return null;
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllArticlePrices(){
        //TODO echte code uit databae ophalen
        return null;
    }
}

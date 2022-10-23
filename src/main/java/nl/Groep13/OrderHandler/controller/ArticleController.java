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
    public HashMap<String, Object> getArticles(){
        //TODO echte code uit databae ophalen
        article = new Article(134, 1, 1234, "black", "een layour", "symbool", "iets");
        HashMap<String, Object> wholeArticle = new HashMap<>();
        wholeArticle.put("article", article);
        wholeArticle.put("articlePrice", getAllArticlePrices());
        return wholeArticle;
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    @ResponseBody
    public ArticlePrice getAllArticlePrices(){
        //TODO echte code uit databae ophalen
        articlePrice = new ArticlePrice(1, "iets", 10.4F, 10.6F, 13F, 15.4F, 20);
        return articlePrice;
    }
}

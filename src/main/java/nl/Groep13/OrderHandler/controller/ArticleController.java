package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import nl.Groep13.OrderHandler.service.ArticleDetailService;
import nl.Groep13.OrderHandler.service.ArticlePriceService;
import nl.Groep13.OrderHandler.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleDetailService articleDetailService;
    private final ArticlePriceService articlePriceService;

    private static Article article;
    private static ArticlePrice articlePrice;
    private static ArticleDetail articleDetail;

    private ObjectMapper objectMapper;
    Gson gson = new Gson();

    @Autowired
    public ArticleController(ArticleService articleService, ArticleDetailService articleDetailService, ArticlePriceService articlePriceService) {
        this.articleService = articleService;
        this.articleDetailService = articleDetailService;
        this.articlePriceService = articlePriceService;
    }

    /**
     *
     * This part is for the articles
     */

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(
                this.articleService.getAllArticles()
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Article> getArticle(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return this.articleService.getArticle(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public Optional<Article> updateArticle(@PathVariable Long id, @RequestParam Map<String, String> article) throws JsonMappingException, JsonProcessingException {
        String articleToJson = gson.toJson(article);
        Article newArticle = gson.fromJson(articleToJson, Article.class);
        return this.articleService.updateArticle(id, Optional.of(newArticle));
    }

    /**
     *
     * This part is for the article details
     */

    @GetMapping(value = "/details")
    @ResponseBody
    public List<ArticleDetail> getAllArticleDetails(){
        return this.articleDetailService.getAllArticleDetails();
    }

    @PutMapping(value = "details/{eancode}")
    @ResponseBody
    public Optional<ArticleDetail> updateArticleDetail(@PathVariable String eancode, @RequestParam Map<String, String> articleDetail) throws JsonMappingException, JsonProcessingException {
        String articleDetailToJson = gson.toJson(articleDetail);
        ArticleDetail newArticleDetail = gson.fromJson(articleDetailToJson, ArticleDetail.class);
        return this.articleDetailService.updateArticle(eancode, Optional.of(newArticleDetail));
    }

    /**
     *
     * This part is for the article prices
     */

    @GetMapping(value = "/prices")
    @ResponseBody
    public List<ArticlePrice> getAllArticlePrices(){
        return articlePriceService.getAllArticlePrices();
    }

    @GetMapping(value = "/prices/{id}")
    @ResponseBody
    public Optional<ArticlePrice> getArticleById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return articlePriceService.getArticlePriceById(id);
    }

}

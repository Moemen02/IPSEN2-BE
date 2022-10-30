package nl.Groep13.OrderHandler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nl.Groep13.OrderHandler.model.Article;
import nl.Groep13.OrderHandler.model.ArticleDetail;
import nl.Groep13.OrderHandler.model.ArticlePrice;
import nl.Groep13.OrderHandler.model.CompleteArticle;
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

    private static CompleteArticle completeArticle;
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

    @DeleteMapping
    public ResponseEntity<Boolean> deleteArticle(@PathVariable final int id) {
        try {
            articleService.deleteArticle((long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Boolean> addArticle(@RequestBody Article article) {
        if (this.articleService.addArticle(article) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
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

    @GetMapping(value = "/details/{eancode}")
    @ResponseBody
    public Optional<ArticleDetail> getArticleById(@PathVariable String eancode) throws ChangeSetPersister.NotFoundException {
        return articleDetailService.getArticleDetailByEancode(eancode);
    }

    @DeleteMapping(value = "/details/{eancode}")
    public ResponseEntity<Boolean> deleteArticleDetail(@PathVariable final String eancode) {
        try {
            articleDetailService.deleteArticleDetail(eancode);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/details")
    public ResponseEntity<Boolean> addArticleDetail(@RequestBody ArticleDetail articleDetail) {
        if (this.articleDetailService.addArticleDetail(articleDetail) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
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

    @PutMapping(value = "prices/{id}")
    @ResponseBody
    public Optional<ArticlePrice> updateArticlePrice(@PathVariable Long id, @RequestParam Map<String, String> articlePrice) throws JsonMappingException, JsonProcessingException {
        String articlePriceToJson = gson.toJson(articlePrice);
        ArticlePrice newArticlePrice = gson.fromJson(articlePriceToJson, ArticlePrice.class);
        return this.articlePriceService.updateArticlePrice(id, Optional.of(newArticlePrice));
    }

    @DeleteMapping(value = "/prices/{id}")
    public ResponseEntity<Boolean> deleteArticlePrice(@PathVariable final int id) {
        try {
            articlePriceService.deleteArticlePrice((long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/prices")
    public ResponseEntity<Boolean> addArticlePrice(@RequestBody final ArticlePrice articlePrice) {
        if (this.articlePriceService.addArticlePrice(articlePrice) == null) {
            return ResponseEntity.badRequest().body(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    /**
     *
     * CompleteArticle functions
     */

    @PostMapping(value = "/completearticle")
    public ResponseEntity addCompleteArticle(@RequestBody CompleteArticle completeArticle) {
        if(this.articlePriceService.addArticlePrice(completeArticle.getArticlePrice()) == null || this.articleDetailService.addArticleDetail(completeArticle.getArticleDetail()) == null || this.articleService.addArticle(completeArticle.getArticle()) == null) {
            return ResponseEntity.badRequest().body("Lacking all info");
        } else {
            return ResponseEntity.ok("Succes");
        }
        //TODO put all the saves in the correct order after know how to add an article in postmen.
    }
}

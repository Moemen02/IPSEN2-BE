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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    private ArticleService articleService;
    private ArticleDetailService articleDetailService;
    private ArticlePriceService articlePriceService;

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
    public ResponseEntity<Optional<Article>> getArticle(@PathVariable Long id)  {
        try {
            Optional<Article> anArticle = this.articleService.getArticle(id);
            return new ResponseEntity<>(anArticle, HttpStatus.FOUND);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Article>> updateArticle(@PathVariable Long id, @RequestBody Map<String, String> article){
        try{
            String articleToJson = gson.toJson(article);
            Article newArticle = gson.fromJson(articleToJson, Article.class);
            Optional<Article> updatedArticle = this.articleService.updateArticle(id, Optional.of(newArticle));
            return new ResponseEntity<>(updatedArticle, HttpStatus.CREATED);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
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
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        if (article == null) {
            throw new NullPointerException("Article is empty");
        } else {
            return ResponseEntity.ok(article);
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

    @PutMapping(value = "/details/{eancode}")
    @ResponseBody
    public Optional<ArticleDetail> updateArticleDetail(@PathVariable String eancode, @RequestBody Map<String, String> articleDetail) throws JsonMappingException, JsonProcessingException {
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
    public ResponseEntity<ArticleDetail> addArticleDetail(@RequestBody ArticleDetail articleDetail) {
        if (articleDetail == null) {
            throw new NullPointerException("ArticleDetail is empty");
        } else {
            return ResponseEntity.ok(articleDetail);
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
    public Optional<ArticlePrice> updateArticlePrice(@PathVariable Long id, @RequestBody Map<String, String> articlePrice) throws JsonMappingException, JsonProcessingException {
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
    public ResponseEntity<ArticlePrice> addArticlePrice(@RequestBody final ArticlePrice articlePrice) {
        if (articlePrice == null) {
            throw new NullPointerException("ArticlePrice is empty");
        } else {
            return ResponseEntity.ok(articlePrice);
        }
    }

    /**
     *
     * CompleteArticle functions
     */

    @PostMapping(value = "/completearticle")
    public ResponseEntity<String> addCompleteArticle(@RequestBody CompleteArticle completeArticle) {
        if (this.articlePriceService.addArticlePrice(completeArticle.getArticlePrice()) == null || this.articleDetailService.addArticleDetail(completeArticle.getArticleDetail()) == null || this.articleService.addArticle(completeArticle.getArticle()) == null) {
            return ResponseEntity.badRequest().body("Lacking important info");
        } else {
            articlePriceService.addArticlePrice(completeArticle.getArticlePrice());
            articleDetailService.addArticleDetail(completeArticle.getArticleDetail());
            articleService.addArticle(completeArticle.getArticle());
            return ResponseEntity.ok("Info added to the database");
        }
    }
}

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

    /**
     * This function is used to get all Articles in a List passed from ArticleSerivce.
     * @return List<Article>
     */
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(
                this.articleService.getAllArticles()
        );
    }

    /**
     * This function is used to get an Article based in the given id to be pased by ArticleService.
     * @param id
     * @return Article
     */
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

    /**
     * This function is used to update an Article based on its id and replace the attributes based on the given Article in Map<String, String>.
     * @param id
     * @param article as a directory of key String with a value of String.
     * @return Optional<Article></>
     */
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

    /**
     * This function passes on Article on given id to be deleted to ArticleService.
     * @param id
     * @return boolean as ResponseEntity if Article was deleted or not.
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteArticle(@PathVariable final int id) {
        try {
            articleService.deleteArticle((long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    /**
     * This function gives the ArticleService an Article to be added to the database.
     * @param article
     * @return
     */
    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        if (article == null) {
            throw new NullPointerException("Article is empty");
        } else {
            articleService.addArticle(article);
            return ResponseEntity.ok(article);
        }
    }

    /**
     *
     * This part is for the article details
     */

    /**
     * This function is used to get all ArticlesDetails in a List passed from ArticleDetailSerivce.
     * @return List<ArticleDetail>
     */
    @GetMapping(value = "/details")
    @ResponseBody
    public List<ArticleDetail> getAllArticleDetails(){
        return this.articleDetailService.getAllArticleDetails();
    }

    /**
     * This function is used to update an ArticleDetail based on its eancode and replace the attributes based on the given ArticleDetail in Map<String, String>.
     * @param eancode
     * @param articleDetail
     * @return ArticleDetail
     * @throws JsonMappingException In case of any mapping problems with given directory.
     * @throws JsonProcessingException Leaves errors in case of problems processing the given directory.
     */
    @PutMapping(value = "/details/{eancode}")
    @ResponseBody
    public Optional<ArticleDetail> updateArticleDetail(@PathVariable String eancode, @RequestBody Map<String, String> articleDetail) throws JsonMappingException, JsonProcessingException {
        String articleDetailToJson = gson.toJson(articleDetail);
        ArticleDetail newArticleDetail = gson.fromJson(articleDetailToJson, ArticleDetail.class);
        return this.articleDetailService.updateArticle(eancode, Optional.of(newArticleDetail));
    }

    /**
     * This function is used to get an ArticleDetail based in the given eancode to be passed by ArticleDetailService.
     * @param eancode
     * @return Optional<Article>
     * @throws ChangeSetPersister.NotFoundException Error if ArticleDetail isn't present in database.
     */
    @GetMapping(value = "/details/{eancode}")
    @ResponseBody
    public Optional<ArticleDetail> getArticleById(@PathVariable String eancode) throws ChangeSetPersister.NotFoundException {
        return articleDetailService.getArticleDetailByEancode(eancode);
    }

    /**
     * This function passes on ArticleDetail on given eancode to be deleted to ArticleDetailService.
     * @param eancode
     * @return boolean as ResponseEntity if ArticleDetail was deleted or not.
     */
    @DeleteMapping(value = "/details/{eancode}")
    public ResponseEntity<Boolean> deleteArticleDetail(@PathVariable final String eancode) {
        try {
            articleDetailService.deleteArticleDetail(eancode);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    /**
     * This function gives the ArticleDetailService an ArticleDetail to be added to the database.
     * @param articleDetail
     * @return ResponseEntity of passed ArticleDetail.
     */
    @PostMapping(value = "/details")
    public ResponseEntity<ArticleDetail> addArticleDetail(@RequestBody ArticleDetail articleDetail) {
        if (articleDetail == null) {
            throw new NullPointerException("ArticleDetail is empty");
        } else {
            articleDetailService.addArticleDetail(articleDetail);
            return ResponseEntity.ok(articleDetail);
        }
    }

    /**
     *
     * This part is for the article prices
     */

    /**
     * This function is used to get all ArticlesPrices in a List passed from ArticlePriceSerivce.
     * @return List<ArticlePrice>
     */
    @GetMapping(value = "/prices")
    @ResponseBody
    public List<ArticlePrice> getAllArticlePrices(){
        return articlePriceService.getAllArticlePrices();
    }

    /**
     * This function is used to get an ArticlePrice based in the given id to be passed by ArticlePriceService.
     * @param id
     * @return Optional<ArticlePrice>
     * @throws ChangeSetPersister.NotFoundException thrown if ArticlePrice is not foudn in database.
     */
    @GetMapping(value = "/prices/{id}")
    @ResponseBody
    public Optional<ArticlePrice> getArticleById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return articlePriceService.getArticlePriceById(id);
    }

    /**
     * This function is used to update an ArticlePrice based on its id and replace the attributes based on the given ArticlePrice in Map<String, String>.
     * @param id
     * @param articlePrice
     * @return ArticlePrice
     * @throws JsonMappingException In case of any mapping problems with given directory.
     * @throws JsonProcessingException Leaves errors in case of problems processing the given directory.
     */
    @PutMapping(value = "prices/{id}")
    @ResponseBody
    public Optional<ArticlePrice> updateArticlePrice(@PathVariable Long id, @RequestBody Map<String, String> articlePrice) throws JsonMappingException, JsonProcessingException {
        String articlePriceToJson = gson.toJson(articlePrice);
        ArticlePrice newArticlePrice = gson.fromJson(articlePriceToJson, ArticlePrice.class);
        return this.articlePriceService.updateArticlePrice(id, Optional.of(newArticlePrice));
    }

    /**
     * This function passes on ArticlePrice on given id to be deleted to ArticlePriceService.
     * @param id
     * @return boolean if the given ArticlePrice was deleted.
     */
    @DeleteMapping(value = "/prices/{id}")
    public ResponseEntity<Boolean> deleteArticlePrice(@PathVariable final int id) {
        try {
            articlePriceService.deleteArticlePrice((long) id);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

    /**
     * This function gives the ArticlePriceService an ArticlePrice to be added to the database.
     * @param articlePrice
     * @return ResponseEntity of passed ArticlePrice.
     */
    @PostMapping(value = "/prices")
    public ResponseEntity<ArticlePrice> addArticlePrice(@RequestBody final ArticlePrice articlePrice) {
        if (articlePrice == null) {
            throw new NullPointerException("ArticlePrice is empty");
        } else {
            articlePriceService.addArticlePrice(articlePrice);
            return ResponseEntity.ok(articlePrice);
        }
    }

    /**
     *
     * CompleteArticle functions
     */

    /**
     * A function to pass a completeArticle to ArticleService, ArticleDetailService and ArticlePrice to add each attribute to the respective table.
     * @param completeArticle
     * @return String with a message that tells if everything was sent or not.
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

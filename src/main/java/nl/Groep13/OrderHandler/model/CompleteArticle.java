package nl.Groep13.OrderHandler.model;

public class CompleteArticle {

    private Article article;
    private ArticleDetail articleDetail;
    private ArticlePrice articlePrice;

    public CompleteArticle(Article article, ArticleDetail articleDetail, ArticlePrice articlePrice) {
        this.article = article;
        this.articleDetail = articleDetail;
        this.articlePrice = articlePrice;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public ArticleDetail getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(ArticleDetail articleDetail) {
        this.articleDetail = articleDetail;
    }


    public ArticlePrice getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(ArticlePrice articlePrice) {
        this.articlePrice = articlePrice;
    }
}

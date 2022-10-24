package nl.Groep13.OrderHandler.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    private int articleNumber;
    private int priceId;
    private int eanCode;
    private String color;
    private String layout;
    private String washSymbol;
    private String composition;
    //    private Location location;

    public Article(int articleNumber, int priceId, int eanCode, String color, String layout, String washSymbol, String composition) {
        this.articleNumber = articleNumber;
        this.priceId = priceId;
        this.eanCode = eanCode;
        this.layout = layout;
        this.washSymbol = washSymbol;
        this.composition = composition;
    }

    public Article() {

    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getEanCode() {
        return eanCode;
    }

    public void setEanCode(int eanCode) {
        this.eanCode = eanCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getWashSymbol() {
        return washSymbol;
    }

    public void setWashSymbol(String washSymbol) {
        this.washSymbol = washSymbol;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}

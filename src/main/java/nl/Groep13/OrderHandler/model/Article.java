package nl.Groep13.OrderHandler.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    private Long id;

    private Long priceid;

    private String eancode;

    private String color;

    private String layout;

    private String washsymbol;

    private String composition;


    public Article(Long id, Long priceid, String eancode, String color, String layout, String washsymbol, String composition) {
        this.id = id;
        this.priceid = priceid;
        this.eancode = eancode;
        this.color = color;
        this.layout = layout;
        this.washsymbol = washsymbol;
        this.composition = composition;
    }

    public Article() {

    }

    public Long getArticleId() {
        return id;
    }
    public void setArticleId(Long id) {
        this.id = id;
    }

    public Long getPriceid() {
        return priceid;
    }

    public void setPriceid(Long priceid) {
        this.priceid = priceid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEancode() {
        return eancode;
    }

    public void setEancode(String eancode) {
        this.eancode = eancode;
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

    public String getWashsymbol() {
        return washsymbol;
    }

    public void setWashsymbol(String washsymbol) {
        this.washsymbol = washsymbol;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}

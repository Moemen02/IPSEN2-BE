package nl.Groep13.OrderHandler.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    private long id;
    private int priceid;
    private int eancode;
    private String color;
    private String layout;
    private String washsymbol;
    private String composition;
    //    private Location location;

    public Article(int id, int priceid, int eancode, String color, String layout, String washsymbol, String composition) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriceid() {
        return priceid;
    }

    public void setPriceid(int priceid) {
        this.priceid = priceid;
    }

    public int getEancode() {
        return eancode;
    }

    public void setEancode(int eancode) {
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

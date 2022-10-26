package nl.Groep13.OrderHandler.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articlenumber")
    private Long id;

    @Column(name = "price_id")
    private Long priceid;

    @Column(name = "detail_id")
    private Long detailid;

    @Column
    private String color;

    @Column
    private String layout;

    @Column
    private String washsymbol;

    @Column
    private String composition;
    //    private Location location;

    public Article(Long id, Long priceid, Long detailid, String color, String layout, String washsymbol, String composition) {
        this.id = id;
        this.priceid = priceid;
        this.detailid = detailid;
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

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
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

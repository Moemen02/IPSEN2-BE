package nl.Groep13.OrderHandler.model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ToString
@Table(name = "article_detail")
public class ArticleDetail {

    @Id
    private String eancode;
    private String productgroup;
    private String supplier;

    public ArticleDetail(String eancode, String productgroup, String supplier) {
        this.eancode = eancode;
        this.productgroup = productgroup;
        this.supplier = supplier;
    }

    public ArticleDetail() {

    }

    public String getEancode() {
        return eancode;
    }

    public void setEancode(String eancode) {
        this.eancode = eancode;
    }

    public String getProductgroup() {
        return productgroup;
    }

    public void setProductgroup(String productgroup) {
        this.productgroup = productgroup;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

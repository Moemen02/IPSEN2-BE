package nl.Groep13.OrderHandler.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

    public String getProductGroup() {
        return productgroup;
    }

    public void setProductGroup(String productGroup) {
        this.productgroup = productGroup;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

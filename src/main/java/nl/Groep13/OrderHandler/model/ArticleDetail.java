package nl.Groep13.OrderHandler.model;

public class ArticleDetail {

    private Long id;
    private String eancode;
    private String productGroup;
    private String supplier;

    public ArticleDetail(Long id ,String eancode, String productGroup, String supplier) {
        this.id = id;
        this.eancode = eancode;
        this.productGroup = productGroup;
        this.supplier = supplier;
    }

    public Long getId() { return id; }

    public void setId(Long id){ this.id = id; }

    public String getEancode() {
        return eancode;
    }

    public void setEancode(String eancode) {
        this.eancode = eancode;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

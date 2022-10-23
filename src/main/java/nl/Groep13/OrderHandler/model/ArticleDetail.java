package nl.Groep13.OrderHandler.model;

public class ArticleDetail {
    private int eancode;
    private String productGroup;
    private String supplier;

    public ArticleDetail(int eancode, String productGroup, String supplier) {
        this.eancode = eancode;
        this.productGroup = productGroup;
        this.supplier = supplier;
    }

    public int getEancode() {
        return eancode;
    }

    public void setEancode(int eancode) {
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

package nl.Groep13.OrderHandler.model;

public class ArticlePrice {
    private Long id;
    private String type;
    private float width;
    private float ptrWidth;
    private float ptrLength;
    private float vPrice;
    private float aPrice;
    private String description;

    public ArticlePrice(Long id, String type, float width, float ptrWidth, float ptrLength, float vPrice, float aPrice, String description) {
        this.id = id;
        this.type = type;
        this.width = width;
        this.ptrWidth = ptrWidth;
        this.ptrLength = ptrLength;
        this.vPrice = vPrice;
        this.aPrice = aPrice;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getPtrWidth() {
        return ptrWidth;
    }

    public void setPtrWidth(float ptrWidth) {
        this.ptrWidth = ptrWidth;
    }

    public float getPtrLength() {
        return ptrLength;
    }

    public void setPtrLength(float ptrLength) {
        this.ptrLength = ptrLength;
    }

    public float getvPrice() {
        return vPrice;
    }

    public void setvPrice(float vPrice) {
        this.vPrice = vPrice;
    }

    public float getaPrice() {
        return aPrice;
    }

    public void setaPrice(float aPrice) {
        this.aPrice = aPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

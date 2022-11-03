package nl.Groep13.OrderHandler.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

    @Id
    private Long articlenumber;
    private String type_storage;
    private String branch;

    public Location() {
    }

    public Location(Long articlenumber, String type_storage, String branch) {
        this.articlenumber = articlenumber;
        this.type_storage = type_storage;
        this.branch = branch;
    }

    public Long getArticlenumber() {
        return articlenumber;
    }

    public void setArticlenumber(Long articlenumber) {
        this.articlenumber = articlenumber;
    }

    public String getType_storage() {
        return type_storage;
    }

    public void setType_storage(String type_storage) {
        this.type_storage = type_storage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

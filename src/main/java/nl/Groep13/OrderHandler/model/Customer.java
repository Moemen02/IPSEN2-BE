package nl.Groep13.OrderHandler.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;
    private int articlenumber;
    private Long addressid;

    @NotBlank(message = "Name is mandatory")
    private String name;

    public Customer() {
    }

    public Customer(Long id, int articlenumber, Long addressid, String name) {
        this.id = id;
        this.articlenumber = articlenumber;
        this.addressid = addressid;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getArticlenumber() {
        return articlenumber;
    }

    public void setArticlenumber(int articlenumber) {
        this.articlenumber = articlenumber;
    }

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

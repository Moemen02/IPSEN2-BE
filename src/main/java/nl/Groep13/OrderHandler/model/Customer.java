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
    private String name;
    private boolean perserved_fabric;
    private boolean retour_fabric;

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", articlenumber=" + articlenumber +
                ", addressid=" + addressid +
                ", name='" + name + '\'' +
                ", perserved_fabric=" + perserved_fabric +
                ", retour_fabric=" + retour_fabric +
                '}';
    }

    public Customer(Long id, int articlenumber, Long addressid, String name, boolean perserved_fabric, boolean retour_fabric) {
        this.id = id;
        this.articlenumber = articlenumber;
        this.addressid = addressid;
        this.name = name;
        this.perserved_fabric = perserved_fabric;
        this.retour_fabric = retour_fabric;
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

    public boolean isPerserved_fabric() {
        return perserved_fabric;
    }

    public void setPerserved_fabric(boolean perserved_fabric) {
        this.perserved_fabric = perserved_fabric;
    }

    public boolean isRetour_fabric() {
        return retour_fabric;
    }

    public void setRetour_fabric(boolean retour_fabric) {
        this.retour_fabric = retour_fabric;
    }
}

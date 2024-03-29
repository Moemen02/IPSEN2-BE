package nl.Groep13.OrderHandler.model;


import javax.persistence.*;

@Entity
@Table(name = "lorder")
public class lOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int articlenumber;
    private int customerid;
    private int claimed_by;

    public lOrder() {
    }

    public lOrder(int articlenumber, int customerid, int claimed_by) {
        this.articlenumber = articlenumber;
        this.customerid = customerid;
        this.claimed_by = claimed_by;
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

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getClaimed_by() {
        return claimed_by;
    }

    public void setClaimed_by(int claimed_by) {
        this.claimed_by = claimed_by;
    }

}

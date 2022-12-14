package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;
import nl.Groep13.OrderHandler.model.Customer;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Waste_order")
public class WasteOrder {

    //Eigenaar van het materiaal, oftewel de klant
    //Ordernummer
    //Aantal meter restant
    //Stofnaam, -kleur en -samenstelling

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WasteID", referencedColumnName = "ID")
    private Waste WasteID;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "CustomerID", referencedColumnName = "ID")
    private Long CustomerID;
    private boolean Finished;

    public WasteOrder(Long ID, Waste wasteID, Long customerID, boolean finished) {
        this.ID = ID;
        WasteID = wasteID;
        CustomerID = customerID;
        Finished = finished;
    }

    public WasteOrder() {
    }
}

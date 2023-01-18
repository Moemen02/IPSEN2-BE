package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer")
public class CustomerV2 {

    @Id
    private Long ID;
    private String Name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressID", referencedColumnName = "id")
    private Address AddressID;

    public CustomerV2(Long ID, String name, Address addressID) {
        this.ID = ID;
        Name = name;
        AddressID = addressID;
    }
}

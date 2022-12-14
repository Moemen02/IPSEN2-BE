package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer")
public class CustomerV2 {

    @Id
    private Long ID;
    private String Name;
    private String AddressID;

    public CustomerV2(String name, String addressID) {
        Name = name;
        AddressID = addressID;
    }
}

package nl.Groep13.OrderHandler.model.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String Postal_code;
    private int Housenumber;
    private String Streetname;

    public Address(Long ID, String postal_code, int housenumber, String streetname) {
        this.ID = ID;
        Postal_code = postal_code;
        Housenumber = housenumber;
        Streetname = streetname;
    }

    public Address() {
    }
}

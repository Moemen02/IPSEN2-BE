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
    private Long id;
    private String postal_code;
    private int housenumber;
    private String streetname;

    public Address(Long id, String postal_code, int housenumber, String streetname) {
        this.id = id;
        this.postal_code = postal_code;
        this.housenumber = housenumber;
        this.streetname = streetname;
    }

    public Address() {
    }
}

package nl.Groep13.OrderHandler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "address")
public class Adress {

    @Id
    private Long id;
    private String postalcode;
    private String housenumber;
    private String streetname;
}

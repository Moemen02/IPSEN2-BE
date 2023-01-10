package nl.Groep13.OrderHandler.model.v2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    private String eancode;
    private String color;
    private String composition;
    private String articlenumber;
    private String description;
    private String type;
    private int weight;



}

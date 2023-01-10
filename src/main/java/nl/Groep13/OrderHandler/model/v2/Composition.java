package nl.Groep13.OrderHandler.model.v2;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Composition {
    private int amount;
    private String type;
    private String condition;
}

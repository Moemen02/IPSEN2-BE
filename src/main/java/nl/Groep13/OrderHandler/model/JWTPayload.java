package nl.Groep13.OrderHandler.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JWTPayload {
    String jwtToken;
    String message;
    boolean success;
}

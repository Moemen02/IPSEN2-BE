package nl.Groep13.OrderHandler.security;

import nl.Groep13.OrderHandler.model.UserRole;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email, UserRole role, String name, Long id) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("role", role.toString())
                .withClaim("name", name)
                .withClaim("id", id)
                .withIssuedAt(new Date())
                .withIssuer("Van Der Lelie")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("Van Der Lelie")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}

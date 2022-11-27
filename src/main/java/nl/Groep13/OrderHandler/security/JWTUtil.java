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

    /**
     * This function returns a JWT token with the user email, role, name and id,
     * This data is used in the frond-end of the applicaton.
     * The secret is sored in the ./recources/application.properties
     * @param email - email of user
     * @param role - current role of user: ADMIN/MEDEWERKER - existing roles can be found in model/UserRole
     * @param name - name of user
     * @param id - id of user as primary key in database
     * @return a string containing the JWT token
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     */
    public String generateToken(String email, UserRole role, String name, Long id, boolean defaultPass) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("role", role.toString())
                .withClaim("name", name)
                .withClaim("id", id)
                .withClaim("defaultPass", defaultPass)
                .withIssuedAt(new Date())
                .withIssuer("Van Der Lelie")
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * validate jwt token with secret from ./recources/application.properties,
     * @param token - JWT token
     * @return returns email form user with token and secret
     * @throws JWTVerificationException
     */
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("Van Der Lelie")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}

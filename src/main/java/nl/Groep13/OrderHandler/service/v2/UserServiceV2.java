package nl.Groep13.OrderHandler.service.v2;

import lombok.AllArgsConstructor;
import nl.Groep13.OrderHandler.DAO.v2.UserDAOV2;
import nl.Groep13.OrderHandler.model.v2.UserV2;
import nl.Groep13.OrderHandler.record.ChangePassword;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.v2.UserRepositoryV2;
import nl.Groep13.OrderHandler.security.JWTUtil;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceV2 implements UserDetailsService {

    private static final String USER_ALREADY_EXISTS = "gebuiker bestaat al";
    private static final String COULD_NOT_FIND_USER_WITH_EMAIL = "Could not findUser with email:";
    private static final String DEFAULT_PASSWORD = "Medewerker@";
    private static final String PASSWORD_DOES_NOT_MATCH = "Wachtwoord komt niet overeen";

    private final String ROLE_PREFIX = "ROLE_";
    private UserDAOV2 userDAOV2;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepositoryV2 userRepositoryV2;

    /**
     * Match user credentials with de credentials in database.
     * @param request - email and password
     * @param authManager
     * @return JWT token if user exists and credentials match
     */
    public String login(LoginRequest request, AuthenticationManager authManager) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authManager.authenticate(authInputToken);
        UserV2 user = userDAOV2.getUserByEmail(request.email()).get();
        return jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getName(), user.getId(), user.isDefault_pass());
    }

    /**
     * Get User model form database with email.
     * @param email - user email
     * @return - user model
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserV2> userRes = userDAOV2.getUserByEmail(email);
        if (userRes.isEmpty()) throw new UsernameNotFoundException(COULD_NOT_FIND_USER_WITH_EMAIL + email);
        UserV2 user = userRes.get();


        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole())));
    }

    /**
     * Register new user in database and generate new JWT token.
     * @param registerRequest - email, name, password, role.
     * @return - new JWT token.
     */
    public String register(RegisterRequest registerRequest)  {
        Optional<UserV2> user = userDAOV2.getUserByEmail(registerRequest.email());
        if (user.isPresent()) return USER_ALREADY_EXISTS;

//        String encodedPass = passwordEncoder.encode(UUID.randomUUID().toString().replace("-", "").substring(0,7));
        String encodedPass = passwordEncoder.encode(DEFAULT_PASSWORD);
        UserV2 newUser = new UserV2(registerRequest.name(), registerRequest.email(), registerRequest.role(), encodedPass, true);
        newUser.setPassword(encodedPass);
        userRepositoryV2.save(newUser);
        return jwtUtil.generateToken(newUser.getEmail(), newUser.getRole(), newUser.getName(), newUser.getId(), false);
    }

    public Optional<UserV2> getUserById(Long id) {
        return userDAOV2.getUserById(id);
    }

    public String changePassword(ChangePassword body, AuthenticationManager authManager) {
        Optional<UserV2> userRes = userDAOV2.getUserByEmail(body.email());
        if (userRes.isEmpty()) throw new UsernameNotFoundException(COULD_NOT_FIND_USER_WITH_EMAIL + body.email());
        UserV2 user = userRes.get();

        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(body.email(), body.oldPassword());
        authManager.authenticate(authInputToken);

        String encodedPass = passwordEncoder.encode(body.newPassword());
        user.setPassword(encodedPass);
        user.setDefault_pass(false);
        userDAOV2.updatePassword(user.getId(),user);

        return jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getName(), user.getId(), user.isDefault_pass());
    }
}
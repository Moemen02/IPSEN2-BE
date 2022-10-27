package nl.Groep13.OrderHandler.service;

import lombok.AllArgsConstructor;
import nl.Groep13.OrderHandler.DAO.UserDAO;
import nl.Groep13.OrderHandler.model.User;
import nl.Groep13.OrderHandler.record.LoginRequest;
import nl.Groep13.OrderHandler.repository.UserRepository;
import nl.Groep13.OrderHandler.security.JWTUtil;
import nl.Groep13.OrderHandler.record.RegisterRequest;
import nl.Groep13.OrderHandler.exception.UserAlreadyExistAuthenticationException;
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
public class UserService implements UserDetailsService {

    private final String ROLE_PREFIX = "ROLE_";
    private UserDAO userDAO;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;


    public String login(LoginRequest request, AuthenticationManager authManager) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authManager.authenticate(authInputToken);
        return jwtUtil.generateToken(request.email());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userRes = userDAO.getUserByEmail(email);
        if (userRes.isEmpty()) throw new UsernameNotFoundException("Could not findUser with email = " + email);
        User user = userRes.get();


        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole())));
    }

    public String register(RegisterRequest registerRequest) throws UserAlreadyExistAuthenticationException {

        Optional<User> user = userDAO.getUserByEmail(registerRequest.email());

        if (user.isPresent()) {
            throw new UserAlreadyExistAuthenticationException("User already exits");
        }

        User newUser = new User(registerRequest.name(), registerRequest.email(), registerRequest.role(), registerRequest.password());
        String encodedPass = passwordEncoder.encode(registerRequest.password());
        newUser.setPassword(encodedPass);
        userRepository.save(newUser);
        return jwtUtil.generateToken(newUser.getEmail());
    }
}
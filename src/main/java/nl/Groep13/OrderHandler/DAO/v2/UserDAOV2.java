package nl.Groep13.OrderHandler.DAO.v2;

import nl.Groep13.OrderHandler.model.v2.UserV2;
import nl.Groep13.OrderHandler.repository.v2.UserRepositoryV2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAOV2 {

    private final UserRepositoryV2 userRepositoryV2;

    public UserDAOV2(UserRepositoryV2 userRepositoryV2) {
        this.userRepositoryV2 = userRepositoryV2;
    }

    public Optional<UserV2> getUserByEmail(String email) {
        return userRepositoryV2.findByEmail(email);
    }

    public UserV2 updatePassword(Long id, UserV2 newUser) {
        userRepositoryV2.updatePassword(newUser.getPassword(), false, id);
        return newUser;

    }
    public Optional<UserV2> getUserById(Long id) {
        return userRepositoryV2.findById(id);
    }

}

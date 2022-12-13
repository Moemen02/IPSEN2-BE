package nl.Groep13.OrderHandler.repository;

import nl.Groep13.OrderHandler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User user set user.password = ?1, user.defaultpass = ?2 where user.id = ?3")
    void updatePassword(String password, boolean defaultpass, Long id);

}

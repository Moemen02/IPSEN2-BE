package nl.Groep13.OrderHandler.repository.v2;

import nl.Groep13.OrderHandler.model.v2.UserV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepositoryV2 extends JpaRepository<UserV2, Long> {
    Optional<UserV2> findByEmail(String EMail);



    @Modifying
    @Transactional
    @Query("update UserV2 user set user.password = ?1, user.default_pass = ?2 where user.id = ?3")
    void updatePassword(String Password, boolean DefaultPass, Long ID);

}

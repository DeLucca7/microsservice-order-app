package microsservice_order_app.user_service.repositories;

import microsservice_order_app.user_service.v1.model.User;
import microsservice_order_app.user_service.v1.model.UserDetails;
import microsservice_order_app.user_service.v1.model.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAllByActive(Active active);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.userDetails = :userDetails WHERE u.userId = :userId")
    void updateUserDetails(@Param("userId") Long userId, @Param("userDetails") UserDetails userDetails);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.active = :active WHERE u.userId = :userId")
    void updateUserActiveStatus(@Param("userId") Long userId, @Param("active") Active active);
}

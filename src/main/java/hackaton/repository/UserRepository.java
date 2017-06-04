package hackaton.repository;

import hackaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by David on 5/10/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("Select data from User data where data.email = :email")
    public User findByEmail(@Param("email")String email);

    @Query("Select data from User data where data.activationKey = :activationKey")
    public User findByActivationKey(@Param("activationKey") String activationKey);
}

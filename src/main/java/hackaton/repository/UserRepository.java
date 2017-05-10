package hackaton.repository;

import hackaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by David on 5/10/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{

}

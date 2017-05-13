package hackaton.repository;

import hackaton.entity.Profile;
import hackaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    @Query("Select data from Profile data where data.user = :user")
    public Profile findByUser(@Param("user")User user);
}

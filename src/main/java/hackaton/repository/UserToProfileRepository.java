package hackaton.repository;

import hackaton.entity.User;
import hackaton.entity.UserToProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserToProfileRepository extends JpaRepository<UserToProfile, Long> {

    @Query("Select data from UserToProfile data where data.user = :user")
    public UserToProfile findByUser(@Param("user")User user);

    @Query("Select data from UserToProfile data where data.user.email = :email")
    public UserToProfile findByEmail(@Param("email")String email);
}

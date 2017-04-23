package hackaton.repository;

import hackaton.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostingRepository extends JpaRepository<Posting, Long> {

//    @Query("Select data from Posting data wherere data.postingSkills")
}

package hackaton.repository;

import hackaton.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostingRepository extends JpaRepository<Posting, Long> {

}

package hackaton.repository;

import hackaton.entity.CandidateToPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateToPostingRepository extends JpaRepository<CandidateToPosting, Long>{
}

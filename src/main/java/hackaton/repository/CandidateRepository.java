package hackaton.repository;

import hackaton.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("Select data from Candidate data where data.mail =:mail ")
    public Candidate getWithMail(@Param("mail")String email);
}

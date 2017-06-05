package hackaton.repository;

import hackaton.entity.CandidateToPosting;
import hackaton.entity.Posting;
import hackaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateToPostingRepository extends JpaRepository<CandidateToPosting, Long>{

    @Query("Select data.posting from CandidateToPosting data where data.user=:user")
    public List<Posting> getAllAppliedToPostingsByUser(@Param("user") User user );

    @Query("Select data from CandidateToPosting data where data.posting=:posting")
    public List<CandidateToPosting> findAllForPosting(@Param("posting") Posting posting);

    @Modifying
    @Query("Delete from CandidateToPosting data where data.posting=:posting")
    public int deleteAllForPosting(@Param("posting") Posting posting);

    @Query("Select data from CandidateToPosting data where data.posting.uuid = :uuid")
    public List<CandidateToPosting> findAllForPostingUUID(@Param("uuid") String uuid);
}

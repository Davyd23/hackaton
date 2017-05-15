package hackaton.repository;


import hackaton.entity.Posting;
import hackaton.entity.PostingToProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostingToProfileRepository extends JpaRepository<PostingToProfile, Long>{

    @Query("select data from PostingToProfile data where data.posting = :posting")
    public PostingToProfile findByPosting(@Param("posting")Posting posting);

    @Query("select data from PostingToProfile data where data.posting.uuid = :uuid")
    public PostingToProfile findByPostingUUID(@Param("uuid") String uuid);

    @Query("Select data from PostingToProfile data where data.posting.user.email = :email")
    public List<PostingToProfile> findAllForEmail(@Param("email") String email);
}

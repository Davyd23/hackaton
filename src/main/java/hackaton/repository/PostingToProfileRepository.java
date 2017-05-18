package hackaton.repository;


import hackaton.entity.Posting;
import hackaton.entity.PostingToProfile;
import hackaton.entity.Profile;
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

    @Query("Select data.posting from PostingToProfile data where data.profile.creative = :creative and" +
            " data.profile.overtimeWork = :overtimeWork and data.profile.analitical = :analitical and" +
            " data.profile.multitasking = :multitasking and data.profile.teamwork = :teamwork and" +
            " data.profile.workExperience = :work_experience")
    public List<Posting> findAllPostingsForProfile(@Param("creative")Boolean creative, @Param("overtimeWork") Boolean overtime,
                                                   @Param("analitical")Boolean analitical, @Param("multitasking")Boolean multitasking,
                                                   @Param("teamwork")Boolean teamwork, @Param("work_experience")String work_experience);
}

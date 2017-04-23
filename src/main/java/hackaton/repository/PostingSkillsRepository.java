package hackaton.repository;

import hackaton.entity.PostingSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostingSkillsRepository extends JpaRepository<PostingSkills, Long> {

    @Query("Select data.posting from PostingSkills data where data.skill in (:skills)")
    public List<PostingSkills> getAllPostingsForSkills(@Param("skills")List<String> skills);
}

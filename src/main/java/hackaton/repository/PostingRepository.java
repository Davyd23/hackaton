package hackaton.repository;

import hackaton.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostingRepository  extends JpaRepository<Posting, Long>{

  /*  @Query("Select data from Posting data where data.uuid = :uuid")
    public Posting findByUUID(@Param("email") String email);*/

  public Posting findByUuid(String uuid);
}

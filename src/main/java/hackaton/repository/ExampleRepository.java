package hackaton.repository;


import hackaton.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ExampleRepository extends JpaRepository<Example, Long> {


    Example findById(Long id);

    @Query("Select data from Example data")
    List<Example> findAllByMadeQuery();
}

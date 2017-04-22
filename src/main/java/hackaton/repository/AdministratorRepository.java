package hackaton.repository;

import hackaton.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Query("Select data from Administrator data")
    public List<Administrator> getAll();
}

package sportmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sportmanagement.entity.Athlete;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    List<Athlete> findByAgeGreaterThanEqual(int age);

    List<Athlete> findByAgeLessThan(int age);

    List<Athlete> findBySport_NameIgnoreCase(String name);
}
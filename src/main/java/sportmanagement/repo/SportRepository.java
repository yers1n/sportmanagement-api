package sportmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sportmanagement.entity.Sport;

import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Long> {
    Optional<Sport> findByNameIgnoreCase(String name);
}
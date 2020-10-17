package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.CoronaVirus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoronaVirusRepository extends JpaRepository<CoronaVirus, Long> {
}

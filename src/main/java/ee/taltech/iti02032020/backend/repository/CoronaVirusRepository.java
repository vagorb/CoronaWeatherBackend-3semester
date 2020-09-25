package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.CoronaVirus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronaVirusRepository extends JpaRepository<CoronaVirus, Long> {
}

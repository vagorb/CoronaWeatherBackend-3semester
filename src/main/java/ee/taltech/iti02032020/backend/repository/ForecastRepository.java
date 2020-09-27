package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
}

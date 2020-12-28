package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    List<Forecast> findAllByCity(String city);

}

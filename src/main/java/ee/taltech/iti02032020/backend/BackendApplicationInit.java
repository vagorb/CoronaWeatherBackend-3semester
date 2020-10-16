package ee.taltech.iti02032020.backend;

import ee.taltech.iti02032020.backend.model.Forecast;
import ee.taltech.iti02032020.backend.repository.ForecastRepository;
import ee.taltech.iti02032020.backend.service.CoronaVirusService;
import ee.taltech.iti02032020.backend.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BackendApplicationInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }
}

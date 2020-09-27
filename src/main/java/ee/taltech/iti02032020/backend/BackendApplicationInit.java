package ee.taltech.iti02032020.backend;

import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import ee.taltech.iti02032020.backend.request.WeatherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BackendApplicationInit implements CommandLineRunner {


    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

//    private WeatherRequest request;

    @Override
    public void run(String... args) throws Exception {
        List<CoronaVirus> coronaViruses = List.of(
                new CoronaVirus("countryName", "totalCases", "recoveredCases", "totalDeaths", "currentCases")
//                new CoronaVirus("hello")
//                new CoronaVirus("vasja"),
//                new CoronaVirus("dimass"),
//                new CoronaVirus()
        );
        coronaVirusRepository.saveAll(coronaViruses);
//        request.WeatherRequestCity();
//        System.out.println(request.WeatherRequestCity());

    }
}

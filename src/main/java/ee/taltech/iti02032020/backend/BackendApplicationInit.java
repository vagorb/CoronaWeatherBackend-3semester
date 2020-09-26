package ee.taltech.iti02032020.backend;

import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BackendApplicationInit implements CommandLineRunner {


    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    private CoronaRequest request;

    @Override
    public void run(String... args) throws Exception {
        List<CoronaVirus> coronaViruses = List.of(
                new CoronaVirus(),
                new CoronaVirus(),
                new CoronaVirus(),
                new CoronaVirus()
        );
        coronaVirusRepository.saveAll(coronaViruses);

    }
}

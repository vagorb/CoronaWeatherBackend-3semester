package ee.taltech.iti02032020.backend;

import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

public class BackendApplicationInit implements CommandLineRunner {


    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    @Override
    public void run(String... args) throws Exception {
        List<CoronaVirus> coronaViruses = List.of(
                new CoronaVirus("Batman", 1L),
                new CoronaVirus("Superman", 2L),
                new CoronaVirus("Wonder Woman", 3L),
                new CoronaVirus("Spider-Man", 4L)
//                new CoronaVirus("Wolverine"),
//                new CoronaVirus("Thor"),
//                new CoronaVirus("Black Widow"),
//                new CoronaVirus("Captain Marvel"),
//                new CoronaVirus("Wasp"),
//                new CoronaVirus("Hulk")
        );
        coronaVirusRepository.saveAll(coronaViruses);
    }
}

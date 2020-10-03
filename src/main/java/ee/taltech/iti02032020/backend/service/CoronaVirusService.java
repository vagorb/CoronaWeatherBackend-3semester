package ee.taltech.iti02032020.backend.service;

import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.exception.InvalidCountryException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoronaVirusService {
    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    public List<CoronaVirus> findAll() {
        return coronaVirusRepository.findAll();
    }

    public CoronaVirus findById(Long id) {
        return coronaVirusRepository.findById(id)
                .orElseThrow(CityNotFoundException::new);
    }


    public CoronaVirus save(CoronaVirus coronaVirus) {
        if (coronaVirus.getCountryName() == null) {
            throw new InvalidCountryException();
        }
        if (coronaVirus.getId() != null){
            throw new InvalidCountryException();
        }
        return coronaVirusRepository.save(coronaVirus);
    }

    public CoronaVirus update(CoronaVirus coronaVirus, Long id) {
        if (coronaVirus.getCountryName() == null) {
            throw new InvalidCountryException();
        }
        CoronaVirus dbCoronaVirus = findById(id);
        dbCoronaVirus.setCountryName(coronaVirus.getCountryName());
        dbCoronaVirus.setCurrentCases(coronaVirus.getCurrentCases());
        dbCoronaVirus.setRecoveredCases(coronaVirus.getRecoveredCases());
        dbCoronaVirus.setTotalCases(coronaVirus.getTotalCases());
        dbCoronaVirus.setTotalDeaths(coronaVirus.getTotalDeaths());
        return coronaVirusRepository.save(dbCoronaVirus);
    }

    public void delete(Long id) {
        CoronaVirus dbCoronaVirus = findById(id);
        coronaVirusRepository.delete(dbCoronaVirus);
    }
}

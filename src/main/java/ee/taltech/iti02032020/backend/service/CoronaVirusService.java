package ee.taltech.iti02032020.backend.service;

import ee.taltech.iti02032020.backend.exception.CoronaVirusCountryNotFoundException;
import ee.taltech.iti02032020.backend.exception.InvalidCoronaVirusCountryException;
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
                .orElseThrow(CoronaVirusCountryNotFoundException::new);
    }

//     Add exception messages !!!
    public CoronaVirus save(CoronaVirus coronaVirus) {
        if (coronaVirus.getCountryName() == null) {
            throw new InvalidCoronaVirusCountryException();
        }
        if (coronaVirus.getId() != null){
            throw new InvalidCoronaVirusCountryException();
        }
        // save will generate id for object
        return coronaVirusRepository.save(coronaVirus);
    }

    public CoronaVirus update(CoronaVirus coronaVirus, Long id) {
        if (coronaVirus.getCountryName() == null) {
            throw new InvalidCoronaVirusCountryException();
        }
        CoronaVirus dbCoronaVirus = findById(id);
        dbCoronaVirus.setCountryName(coronaVirus.getCountryName());
        // save works as update when id is present
        return coronaVirusRepository.save(dbCoronaVirus);
    }

    public void delete(Long id) {
        CoronaVirus dbCoronaVirus = findById(id);
        coronaVirusRepository.delete(dbCoronaVirus);
    }

}

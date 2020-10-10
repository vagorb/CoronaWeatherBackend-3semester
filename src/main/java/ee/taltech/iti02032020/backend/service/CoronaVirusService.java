package ee.taltech.iti02032020.backend.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.exception.CityNotFoundException;
import ee.taltech.iti02032020.backend.exception.InvalidCountryException;
import ee.taltech.iti02032020.backend.exception.PropertyNotFoundException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CoronaVirusService {

    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    private CoronaRequest coronaRequest = new CoronaRequest();

    public List<CoronaVirus> findAll() {
        return coronaVirusRepository.findAll();
    }

    public CoronaVirus findById(Long id) {
        return coronaVirusRepository.findById(id)
                .orElseThrow(CityNotFoundException::new);
    }


    public CoronaVirus save(CoronaVirus coronaVirus) {
        if (coronaVirus.getCountryName() == null || coronaVirus.getCurrentCases() == null
                || coronaVirus.getRecoveredCases() == null || coronaVirus.getTotalCases() == null) {
            throw new PropertyNotFoundException();
        }
        if (coronaVirus.getId() != null){
            throw new PropertyNotFoundException();
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

    public CoronaVirus getCoronaVirus(String country) throws IOException {
        String coronaInfo = coronaRequest.CoronaRequestCountry(country);
        JsonObject json = new Gson().fromJson(coronaInfo, JsonObject.class);
        int status = json.get("status").getAsInt();
        if (status == 200) {
            CoronaVirus coronaVirus = CoronaVirus.getCoronaVirusFromJson(coronaInfo, country);
            coronaVirusRepository.save(coronaVirus);
            return coronaVirus;
        } else {
            throw new InvalidCountryException();
        }
    }
}

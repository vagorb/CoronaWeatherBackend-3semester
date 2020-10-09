package ee.taltech.iti02032020.backend.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ee.taltech.iti02032020.backend.exception.InvalidCountryException;
import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.repository.CoronaVirusRepository;
import ee.taltech.iti02032020.backend.request.CoronaRequest;
import ee.taltech.iti02032020.backend.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("coronaViruses")
@RestController
public class CoronaVirusController {

    private CoronaRequest coronaRequest = new CoronaRequest();

    @Autowired
    private CoronaVirusRepository coronaVirusRepository;

    @Autowired
    private CoronaVirusService coronaViruses;

    @GetMapping
    public List<CoronaVirus> getCoronaViruses() {
        return coronaViruses.findAll();
    }

    @GetMapping("{id}")
    public CoronaVirus getCoronaVirus(@PathVariable Long id) {
        return coronaViruses.findById(id);
    }

    @PostMapping("country/{country}")
    public CoronaVirus saveCoronaVirus(@RequestBody String country) throws IOException {
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

    @PutMapping("{id}")
    public CoronaVirus updateCoronaVirus(@RequestBody CoronaVirus coronaVirus, @PathVariable Long id) {
        return coronaViruses.update(coronaVirus, id);
    }

    @DeleteMapping("{id}")
    public void deleteCoronaViruse(@PathVariable Long id) {
        coronaViruses.delete(id);
    }
}

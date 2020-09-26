package ee.taltech.iti02032020.backend.controller;


import ee.taltech.iti02032020.backend.model.CoronaVirus;
import ee.taltech.iti02032020.backend.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("coronaViruses")
@RestController
public class CoronaVirusController {


    @Autowired
    private CoronaVirusService coronaViruses;

    @GetMapping
    public List<CoronaVirus> getCoronaViruses() {
        return coronaViruses.findAll();
    }

    @GetMapping("{id}")
    public CoronaVirus getCoronaViruses(@PathVariable Long id) {
        return coronaViruses.findById(id);
    }

    @PostMapping
    public CoronaVirus saveCoronaVirus(@RequestBody CoronaVirus hero) {
        return coronaViruses.save(hero);
    }

    @PutMapping("{id}")
    public CoronaVirus updateCoronaVirus(@RequestBody CoronaVirus hero, @PathVariable Long id) {
        return coronaViruses.update(hero, id);
    }

    @DeleteMapping("{id}")
    public void deleteCoronaViruse(@PathVariable Long id) {
        coronaViruses.delete(id);
    }
}

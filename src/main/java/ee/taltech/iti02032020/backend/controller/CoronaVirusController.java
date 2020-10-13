//package ee.taltech.iti02032020.backend.controller;
//import ee.taltech.iti02032020.backend.model.CoronaVirus;
//import ee.taltech.iti02032020.backend.service.CoronaVirusService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RequestMapping("coronaViruses")
//@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class CoronaVirusController {
//
//    @Autowired
//    private CoronaVirusService coronaViruses;
//
//    @GetMapping
//    public List<CoronaVirus> getCoronaViruses() {
//        return coronaViruses.findAll();
//    }
//
//    @GetMapping("{id}")
//    public CoronaVirus getCoronaVirus(@PathVariable Long id) {
//        return coronaViruses.findById(id);
//    }
//
//    @PostMapping
//    public CoronaVirus saveCoronaVirus(@RequestBody CoronaVirus coronaVirus) {
//        return coronaViruses.save(coronaVirus);
//    }
//
//    @PutMapping("{id}")
//    public CoronaVirus updateCoronaVirus(@RequestBody CoronaVirus coronaVirus, @PathVariable Long id) {
//        return coronaViruses.update(coronaVirus, id);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteCoronaVirus(@PathVariable Long id) {
//        coronaViruses.delete(id);
//    }
//
//    @GetMapping("country/{country}")
//    public CoronaVirus getCoronaVirus(@PathVariable String country) throws IOException {
//        return coronaViruses.getCoronaVirus(country);
//    }
//
//
//}

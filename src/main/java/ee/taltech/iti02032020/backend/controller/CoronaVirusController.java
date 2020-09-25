package ee.taltech.iti02032020.backend.controller;


import ee.taltech.iti02032020.backend.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("coronaViruses")
@RestController
public class CoronaVirusController {


    @Autowired
    private CoronaVirusService coronaViruses;


    @GetMapping
    public String getStringValue() {
        return "THIS IS MY STRING!";
    }
}

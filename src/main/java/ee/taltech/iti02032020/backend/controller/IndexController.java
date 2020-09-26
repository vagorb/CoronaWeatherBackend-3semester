package ee.taltech.iti02032020.backend.controller;


import ee.taltech.iti02032020.backend.model.CoronaVirus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class IndexController {

    @GetMapping
    public String index(){
        return "Hello from Spring Boot";
    }

    @GetMapping("number")
    public Integer someNumber(){
        return 123;
    }

    //todo 1.2 create some endpoint to return some object
    @GetMapping("hero")
    public CoronaVirus someHero(){
        return new CoronaVirus("Superman", 1L);
    }

    //todo 1.3 create an endpoint "greeting", pass name to it to return "Hello <name>"
    @GetMapping("greeting")
    public String greeting(@RequestParam(required = false, defaultValue = "Oleg") String name,
                           @RequestParam(required = false) String job){
        return String.format("Hello %s you good %s", name, job);
    }

    @GetMapping("greeting/{name}")
    public String greeting(@PathVariable String name){
        return String.format("Hello for %s", name);
    }
}

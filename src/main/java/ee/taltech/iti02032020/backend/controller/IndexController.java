package ee.taltech.iti02032020.backend.controller;


import ee.taltech.iti02032020.backend.model.CoronaVirus;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("number")
//    public Integer integer(){
//        return 123;
//    }
//
//    @GetMapping("CoronaVirus")
//    public CoronaVirus someCoronaVirus(){
//        return new CoronaVirus();
//    }
//
//    @GetMapping("greeting")
//    public String greeting(@RequestParam(required = false, defaultValue = "Dima") String name,
//                           @RequestParam(required = false) String job){
//        return String.format("Hello %s you good %s", name, job);
//    }
}

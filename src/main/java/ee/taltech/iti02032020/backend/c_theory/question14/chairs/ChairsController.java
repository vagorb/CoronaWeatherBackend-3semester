package ee.taltech.iti02032020.backend.c_theory.question14.chairs;


import org.springframework.data.domain.Sort;
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

@RequestMapping("chairs")
@RestController
public class ChairsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for chairs.
    // Think a backoffice system for furniture shop like Aatrium or some Kalamaja chair boutique.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query chairs (plural)

    //todo C create a method to query single chair

    //todo D create a method to save a chair

    //todo E create a method to update a chair

    //todo F create a method to delete a chair

    //todo G assuming each chair has a designer (one-to-one relation) create a method to query chair's designer

    //todo H create a method to update chair's name (and nothing else)

    //todo I modify correct method to support searching chairs by type while keeping original functionality

    //todo J modify correct method to support searching chairs by whether chair is in stock while keeping original functionality

    //todo K modify correct method to order/sort chairs
    // * by lowest priced first
    // * by highest priced first
    // (you can assume that by default it searches most popular first)

    // Vassili Gorbatšov
    List<Chair> emptyMethodReturnList(){
        return List.of();
    }

    Chair emptyMethodReturnChair(){
        return new Chair();
    }

    Designer emptyMethodReturnDesigner(){
        return new Designer();
    }

    void emptyMethodVoid(){
    }

    // B K I J
    @GetMapping
    public List<Chair> getAllChairs (@RequestParam(value = "type", required = false) String type,    // I
                                     @RequestParam(value = "stock", required = false) String inStock, // J
                                     @RequestParam(value = "order", defaultValue = "popularity") String order,
                                     @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction) {
        return emptyMethodReturnList();
    }

    // C
    @GetMapping("{id}")
    public Chair getChair(@PathVariable Long id) {
        return emptyMethodReturnChair();
    }

    // D
    @PostMapping
    public void saveChair(@RequestBody Chair chair) {
        emptyMethodVoid();
    }

    // E
    @PutMapping("{id}")
    public void updateChair(@RequestBody Chair chair, @PathVariable Long id) {
        emptyMethodVoid();
    }


    // F
    @DeleteMapping("{id}")
    public Chair deleteChair(@PathVariable Long id) {
        return emptyMethodReturnChair();
    }

    // G or method G2 if we assume, that information about designer is in front, because Chair has property designer,
    // but there were no logic to ask for it, because info about designer is already known.
    // G                                                                    // G2
    @GetMapping("{id}/designer")                                            // @GetMapping("designer")
    public Designer getChairDesigner(@PathVariable Long id) {               // public Designer getChairDesigner(@RequestParam Designer designer) {
        return emptyMethodReturnDesigner();                                 // turn emptyMethodReturnDesigner();
    }                                                                       // }

    // H
    @PutMapping("{id}/name")
    public void updateChair(@RequestParam String name, @PathVariable Long id) {
        emptyMethodVoid();
    }

}

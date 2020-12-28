package ee.taltech.iti02032020.backend.c_theory.question14.lessons;


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

@RequestMapping("Lesson")
@RestController
public class LessonsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think page where you are looking at lessons like echo360.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query lessons (plural)

    //todo C create a method to query single lesson

    //todo D create a method to save a lesson

    //todo E create a method to update a lesson

    //todo F create a method to delete a lesson

    //todo G assuming each Lesson has students (one-to-many relation) create a method to query lesson's students

    //todo H create a method to update lesson's name (and nothing else)

    //todo I modify correct method to support searching lessons by course id while keeping original functionality

    //todo J modify correct method to support searching by year with default being current year (2020)
    // (you can ignore semesters or use year-semester string)

    //todo K modify correct method to order lessons
    // * by most visitors first
    // * by least visitors first
    // (you can assume that by default it searches by predefined lecturer's order)


    List<Lesson> emptyMethodReturnList(){
        return List.of();
    }
    List<Students> emptyMethodReturnList2(){
        return List.of();
    }

    Lesson emptyMethodReturn1(){
        return new Lesson();
    }

    void emptyMethodVoid(){

    }

    //B //I  //J //K
    @GetMapping
    public List<Lesson> getAllLessons(@RequestParam(required = false) String id,
                                      @RequestParam(required = false, defaultValue = "2020") String year,
                                      @RequestParam(value = "orderBy", defaultValue = "lecturersOrder") String orderBy,
                                      @RequestParam(value = "direction", defaultValue = "lecturersDirection" ) Sort.Direction direction) {
        return emptyMethodReturnList();
    }

    //C
    @GetMapping("{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return emptyMethodReturn1();
    }

    //D
    @PostMapping
    public void saveLesson(@RequestBody Lesson lesson) {
//        lessonService.save(lesson);
        emptyMethodVoid();
    }

    //E
    @PutMapping("{id}")
    public void updateLesson(@RequestBody Lesson lesson, @PathVariable Long id) {
        emptyMethodVoid();
    }

    //F
    @DeleteMapping("{id}")
    public void deleteLesson(@PathVariable Long id) {
        emptyMethodVoid();
    }

    //G
    @GetMapping("{id}/students")
    public List<Students> getLessonStudents(@PathVariable Long id) {
//        return lesson.get
        return emptyMethodReturnList2();
    }

    //H
    @PutMapping("{id}/name")
    public void updateLessonName(@RequestParam String name, @PathVariable Long id) {
        emptyMethodVoid();
    }
}

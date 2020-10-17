package ee.taltech.iti02032020.backend.a_theory.question3;

public class Cat {
    String gender;
    String color;
    String favouriteFood;
    Integer age;
    boolean castrated;
    boolean owner;
    String health;


    public Cat(String color, Integer age, String favouriteFood, boolean castrated, boolean owner, String health) {
        this.color = color;
        this.favouriteFood = favouriteFood;
        this.age = age;
        this.castrated = castrated;
        this.owner = owner;
        this.health = health;
    }

    public String showToPerson(Cat cat) {
        // Only shows the most important information to another "User"
        return "This cat is " + cat.gender + "her color is " + cat.color + "her age is " + cat.age;
    }
}

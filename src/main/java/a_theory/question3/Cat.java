package a_theory.question3;

public class Cat extends Animal {
    String favouriteFood;

    public Cat(String color, Integer age, String favouriteFood) {
        super(color, age);
        this.favouriteFood = favouriteFood;
    }

    @Override
    public String toString() {
        return age + " years old " + color + " cat. It likes to eat " + favouriteFood;
    }

    @Override
    public String favouriteFood() {
        return favouriteFood;
    }
}

package a_theory.question3;

public abstract class Animal {
    String color;
    Integer age;

    public Animal(String color, Integer age) {
        this.color = color;
        this.age = age;
    }

    public abstract String toString();

    public abstract String favouriteFood();
}

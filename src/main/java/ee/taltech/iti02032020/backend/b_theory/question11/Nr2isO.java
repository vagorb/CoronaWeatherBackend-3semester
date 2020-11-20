package ee.taltech.iti02032020.backend.b_theory.question11;

import java.util.List;

public class Nr2isO {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does O stand for in SOLID? Explain the principle.
    // Answer : O stands for open-closed principle.
    // Principle states that software entities should be open for extension, but closed for modification.
    // Principle applies to classes, modules, functions etc.
    // The general idea of the principal is that you should be able to add new functionality without changing the existing code.
    // This prevents situations when a change to one of your classes also requires you to change or adapt all depending classes


    //todo B Give an example. Write actual or pseudo code.
}

interface Cemetery {

    public void digGrave();
}

interface HealthCare {
    // Our interface class that we can not change but will use to create code in new classes

    public boolean checkHealth();

}

class HumanHealthCare implements HealthCare, Cemetery {
    // here we create a class that can check humans and they're health
    // the class also implements another interface to deal with situations when people die
    private String financialStability;
    private Boolean allergies;
    private List<String> historyOfDiseases;

    @Override
    public boolean checkHealth() {
        return financialStability.equals("yes") && !allergies && !historyOfDiseases.contains("badDisease");
        // inherited method to check health in general
    }

    public void makeSomeHumanRelatedChecks() {
        // method to check class related specifications
    }

    public void confirmFinancialStability() {
        // method to check class related specifications
    }

    @Override
    public void digGrave() {
        if (!checkHealth()) {
            digGrave();
        }
        // inherited method that buries the dead
    }
}

class AnimalHealthCare implements HealthCare, Cemetery {
    // here we create a class that can check animals and they're health
    // the class also implements another interface to deal with situations when animals die
    private boolean homeless;
    private boolean wild;
    private boolean physicallyOkey;


    @Override
    public boolean checkHealth() {
        return homeless && !wild && physicallyOkey;
        // inherited method to check health in general
    }

    public void checkIfAnimalIsWild() {
        // method to check class related specifications
    }

    @Override
    public void digGrave() {
        if (!checkHealth()) {
            digGrave();
        }
        // inherited method that buries the dead
    }
}

class reptileHealthCare implements  HealthCare {
    // here we create a class that can check reptiles and they're health
    // this class only implements HealthCare because it's not viable to dig graves for reptiles
    private boolean deadly;
    private boolean wild;
    private boolean testable;

    @Override
    public boolean checkHealth() {
        return deadly && !wild && testable;
        // inherited method
    }
}
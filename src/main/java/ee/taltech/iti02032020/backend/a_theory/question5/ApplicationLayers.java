package ee.taltech.iti02032020.backend.a_theory.question5;

public class ApplicationLayers {


    //todo
    // Architects insist on having layered architecture in the back-end... ¯\_(ツ)_/¯

    //todo p1
    // Name 3 layers of back-end architecture. Give a brief description for each.
    // 1 Repository
    // Description: Serves data. Contains methods to read/write from the database.
    // 2 Service
    // Description: Application logic. It contains all methods to realize business logic.
    // 3 Controller
    // Description: Used to make calls to services.

    //todo p2
    // Do you agree with the architects? Why?
    // Yes
    // Because: It is easy to understand purpose of every part, also frontend developers can easily understand what they
    // need to do, because they are working with methods from controller and do not waste time for understanding whole
    // backend code.

    //todo p3
    // We use objects to transport data between different layers.
    // What is the difference between Entity and Dto? What is same between them?
    // Answer:  Dto could be used only to transfer data from one process to another, but Entity could be part of
    // business domain (set of classes that represent objects in the business model). They both transfer data.

}

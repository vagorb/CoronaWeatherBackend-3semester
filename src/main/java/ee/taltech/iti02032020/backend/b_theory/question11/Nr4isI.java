package ee.taltech.iti02032020.backend.b_theory.question11;

import java.util.List;
import java.util.Objects;

public class Nr4isI {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does I stand for in SOLID? Explain the principle.
    // I stands for Interface Segregation Principle. Idea of this principle is separating one big interface for smaller,
    // based on needs of different groups of users. For them should be provided only interfaces, that they need.
    //todo B Give an example. Write actual or pseudo code.

    public interface eSchool {
        // Student marks
        public List<Objects> getMarksForStudent();
        // Missed lessons by student
        public List<Objects> getAbsencesForStudent();
    }


    public interface teacherFeatures extends eSchool{
        // Putting mark for student
        public Object putMark();
        // Adding that student missed lesson
        public Object putAbsence();
    }

    public interface parentFeatures extends eSchool{
        // Add cause of missing
        public String writeCauseOfAbsence();
    }

    public class Teacher implements teacherFeatures {

        @Override
        public List<Objects> getMarksForStudent() {
            return null;
        }

        @Override
        public List<Objects> getAbsencesForStudent() {
            return null;
        }

        @Override
        public Object putMark() {
            return null;
        }

        @Override
        public Object putAbsence() {
            return null;
        }
    }

    public class Parent implements parentFeatures {

        @Override
        public List<Objects> getMarksForStudent() {
            return null;
        }

        @Override
        public List<Objects> getAbsencesForStudent() {
            return null;
        }

        @Override
        public String writeCauseOfAbsence() {
            return null;
        }

    }
    
}

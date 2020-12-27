package ee.taltech.iti02032020.backend;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class BackendApplicationInit implements CommandLineRunner {


    @Autowired
    private AddAdmin addAdmin;

    @Override
    public void run(String... args) {
        addAdmin.addAdminToDatabase();
    }
}

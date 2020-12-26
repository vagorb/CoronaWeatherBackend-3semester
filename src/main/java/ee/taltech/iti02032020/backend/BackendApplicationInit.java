package ee.taltech.iti02032020.backend;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class BackendApplicationInit implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//
//    }

    @Autowired
    private AddAdmin addAdmin;

    @Override
    public void run(String... args) {
        addAdmin.addAdminToDatabase();
    }
}

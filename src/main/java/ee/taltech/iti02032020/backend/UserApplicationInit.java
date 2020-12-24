package ee.taltech.iti02032020.backend;


import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import ee.taltech.iti02032020.backend.security.DbRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
//@Profile("test")
@AllArgsConstructor
public class UserApplicationInit implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole(DbRole.USER);
        usersRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(DbRole.ADMIN);
        usersRepository.save(admin);
    }
}

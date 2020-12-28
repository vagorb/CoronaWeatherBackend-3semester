package ee.taltech.iti02032020.backend;


import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import ee.taltech.iti02032020.backend.security.DbRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@Slf4j
@AllArgsConstructor
public class AddAdmin {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Bean
    CommandLineRunner addAdminToDatabase() {
        return args -> {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole(DbRole.USER);
            user.setHometown("Tallinn");
            usersRepository.save(user);
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(DbRole.ADMIN);
            admin.setHometown("Narva");
            usersRepository.save(admin);
        };
    }
}

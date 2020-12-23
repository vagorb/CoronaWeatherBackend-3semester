package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsername(String username);
}

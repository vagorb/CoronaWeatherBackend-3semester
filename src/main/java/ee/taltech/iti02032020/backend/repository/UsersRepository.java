package ee.taltech.iti02032020.backend.repository;

import ee.taltech.iti02032020.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}

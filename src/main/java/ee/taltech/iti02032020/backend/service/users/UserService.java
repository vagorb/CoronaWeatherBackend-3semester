package ee.taltech.iti02032020.backend.service.users;

import ee.taltech.iti02032020.backend.exception.UserException;
import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import ee.taltech.iti02032020.backend.security.DbRole;
import ee.taltech.iti02032020.backend.service.users.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@AllArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(RegisterDto registerDto) {
        if (isBlank(registerDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(registerDto.getPassword())) {
            throw new UserException("missing password");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(DbRole.USER);
        user.setHometown(registerDto.getHometown());
        usersRepository.save(user);
        //email sent out to confirm it, not necessary fot iti0203
    }

    public List<User> findAll() {
        System.out.println(usersRepository.findAll());
        return usersRepository.findAll();
    }
}

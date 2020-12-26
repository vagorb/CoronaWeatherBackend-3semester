package ee.taltech.iti02032020.backend.service.users;

import ee.taltech.iti02032020.backend.exception.UserException;
import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import ee.taltech.iti02032020.backend.security.DbRole;
import ee.taltech.iti02032020.backend.service.users.dto.RegisterDto;
import ee.taltech.iti02032020.backend.service.users.dto.UpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
        if (isBlank(registerDto.getHometown())) {
            throw new UserException("missing hometown");
        }
        if (usersRepository.findAllByUsername(registerDto.getUsername()).size() == 0) {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRole(DbRole.USER);
            user.setHometown(registerDto.getHometown());
            usersRepository.save(user);
        } else {
            throw new UserException("username already exists");
        }
    }

    public void updateUser(UpdateDto updateDto) {
        if (isBlank(updateDto.getUsername()) && isBlank(updateDto.getHometown())) {
            throw new UserException("Missing username and hometown");
        } if (isBlank(updateDto.getHometown()) && usersRepository.findAllByUsername(updateDto.getOldusername()).size() == 1
                && usersRepository.findAllByUsername(updateDto.getUsername()).size() == 0) {
            User dbUser = usersRepository.findAllByUsername(updateDto.getOldusername()).get(0);
            dbUser.setUsername(updateDto.getUsername());
            usersRepository.save(dbUser);

        } if (isBlank(updateDto.getHometown()) && usersRepository.findAllByUsername(updateDto.getOldusername()).size() == 1
          && usersRepository.findAllByUsername(updateDto.getUsername()).size() == 0) {
            User dbUser = usersRepository.findAllByUsername(updateDto.getOldusername()).get(0);
            dbUser.setHometown(updateDto.getHometown());
            usersRepository.save(dbUser);

        } if (isNotBlank(updateDto.getHometown()) && isNotBlank(updateDto.getUsername())
                && usersRepository.findAllByUsername(updateDto.getOldusername()).size() == 1
        && usersRepository.findAllByUsername(updateDto.getUsername()).size() == 0) {
            User dbUser = usersRepository.findAllByUsername(updateDto.getOldusername()).get(0);
            dbUser.setUsername(updateDto.getUsername());
            dbUser.setHometown(updateDto.getHometown());
            usersRepository.save(dbUser);

        }  else {
            throw new UserException("Username already exists");
        }
    }

    public List<User> findAll() {
        System.out.println(usersRepository.findAll());
        return usersRepository.findAll();
    }
}

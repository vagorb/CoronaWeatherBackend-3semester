package ee.taltech.iti02032020.backend.security;

import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;


/**
 * to load user with username, password and roles (authorities)
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = usersRepository.findAllByUsername(username);
        if (CollectionUtils.isEmpty(users)){
            throw new UsernameNotFoundException(format("username not found: %s", username));
        }
        User user = users.get(0); //this application doesn't protect against duplicate users
        return new MyUser(user.getUsername(), user.getPassword(), user.getHometown(), getAuthorities(user), user.getId(), user.getRole());
    }

    /**
     * convert db roles to GrantedAuthority object
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(DbRole::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * if user is admin, then they get all the roles in application
     */
    private Stream<DbRole> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(DbRole.values());
        }
        return Stream.of(user.getRole());
    }
}


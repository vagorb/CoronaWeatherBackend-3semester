package ee.taltech.iti02032020.backend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUser extends User {

    private Long id;
    private DbRole dbRole;
    private String hometown;


    public MyUser(String username, String password, String hometown, Collection<? extends GrantedAuthority> authorities, Long id, DbRole dbRole) {
        super(username, password, authorities);
        this.id = id;
        this.dbRole = dbRole;
        this.hometown = hometown;
    }
}

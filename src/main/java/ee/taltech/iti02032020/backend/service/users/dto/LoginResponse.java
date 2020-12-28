package ee.taltech.iti02032020.backend.service.users.dto;

import ee.taltech.iti02032020.backend.security.DbRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String username;
    private String token;
    private DbRole role;
    private String hometown;
}

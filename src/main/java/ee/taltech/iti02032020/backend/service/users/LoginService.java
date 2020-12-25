package ee.taltech.iti02032020.backend.service.users;

import ee.taltech.iti02032020.backend.exception.UserException;
import ee.taltech.iti02032020.backend.security.JwtTokenProvider;
import ee.taltech.iti02032020.backend.security.MyUser;
import ee.taltech.iti02032020.backend.service.users.dto.LoginDto;
import ee.taltech.iti02032020.backend.service.users.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getUsername())) {
            throw new UserException("missing username");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("missing password");
        }
        //this will validate that the password is correct (without us validating it explicitly)
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal(); //it is UserDetails and in our case it is MyUser
        String token = jwtTokenProvider.generateToken(myUser);
        return LoginResponse.builder()
                .username(myUser.getUsername())
                .token(token)
                .role(myUser.getDbRole())
                .hometown(myUser.getHometown())
                .build();
    }
}

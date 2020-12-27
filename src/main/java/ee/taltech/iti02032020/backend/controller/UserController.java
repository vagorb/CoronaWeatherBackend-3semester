package ee.taltech.iti02032020.backend.controller;



import ee.taltech.iti02032020.backend.security.Roles;
import ee.taltech.iti02032020.backend.service.users.LoginService;
import ee.taltech.iti02032020.backend.service.users.UserService;
import ee.taltech.iti02032020.backend.service.users.dto.LoginDto;
import ee.taltech.iti02032020.backend.service.users.dto.LoginResponse;
import ee.taltech.iti02032020.backend.service.users.dto.RegisterDto;
import ee.taltech.iti02032020.backend.service.users.dto.UpdateDto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("users")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
        userService.saveUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

    @Secured(Roles.USER)
    @PostMapping("update")
    public ResponseEntity<Void> update(@RequestBody UpdateDto updateDto){
         userService.updateUser(updateDto);
         return ResponseEntity.status(HttpStatus.OK).build();
    }

}

package ee.taltech.iti02032020.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCountryException extends RuntimeException{

    public InvalidCountryException() {
        super("Invalid Country");
    }


}

package gk.defpub.restservice.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * CustomExceptionHandler class.
 * <p>
 * Date: Nov 14, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {UserPermissionException.class})
    public ResponseEntity<String> handlePermissionException(UserPermissionException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }
}

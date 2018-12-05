package gk.defpub.restservice.validation;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustomExceptionHandlerTest class.
 * <p>
 * Date: Nov 19, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class CustomExceptionHandlerTest {
    private CustomExceptionHandler handler = new CustomExceptionHandler();

    @Test
    public void testHandlePermissionException() {
        UserPermissionException exception = new UserPermissionException();
        ResponseEntity<String> response = handler.handlePermissionException(exception);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(exception.getMessage());
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoSuchElementException exception = new NoSuchElementException();
        ResponseEntity<String> response = handler.handleNoSuchElementException(exception);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo(exception.getMessage());
    }
}

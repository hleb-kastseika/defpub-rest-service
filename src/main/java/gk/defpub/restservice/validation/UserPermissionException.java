package gk.defpub.restservice.validation;

/**
 * UserPermissionException class.
 * <p>
 * Date: Nov 14, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class UserPermissionException extends RuntimeException {
    private static final String ERROR_RESPONSE = "{\"error\":\"You don't have enough permissions to do that.\"}";

    public UserPermissionException() {
        super(ERROR_RESPONSE);
    }
}
package gk.defpub.restservice.model;

/**
 * AuthToken class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class AuthToken {
    private String token;

    public AuthToken() {}

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

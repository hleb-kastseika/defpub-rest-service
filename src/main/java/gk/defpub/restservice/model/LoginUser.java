package gk.defpub.restservice.model;

/**
 * LoginUser class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class LoginUser {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

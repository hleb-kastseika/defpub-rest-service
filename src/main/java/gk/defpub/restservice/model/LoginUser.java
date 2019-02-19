package gk.defpub.restservice.model;

import gk.defpub.restservice.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;

/**
 * LoginUser class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class LoginUser {
    @UniqueUsername
    @NotBlank
    private String username;
    @NotBlank
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

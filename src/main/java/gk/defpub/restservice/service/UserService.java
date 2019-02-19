package gk.defpub.restservice.service;

import gk.defpub.restservice.model.LoginUser;
import gk.defpub.restservice.model.User;

import java.util.List;

/**
 * UserService interface.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public interface UserService {

    User save(LoginUser user);

    List<User> findAll();

    void delete(String id);

    User findOne(String username);

    User findById(String id);
}

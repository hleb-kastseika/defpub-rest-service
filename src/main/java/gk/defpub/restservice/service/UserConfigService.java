package gk.defpub.restservice.service;

import gk.defpub.restservice.model.UserConfig;

import java.util.List;

/**
 * UserConfigService interface.
 * <p>
 * Date: Dec 3, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public interface UserConfigService {

    UserConfig save(UserConfig publication);
    List<UserConfig> findAll();
    UserConfig findByUserId(String userId);
    UserConfig findById(String id);
    void delete(String id);
}

package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.UserConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserConfigRepository interface.
 * <p>
 * Date: Dec 3, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Repository
public interface UserConfigRepository extends CrudRepository<UserConfig, String> {

    UserConfig findByUserId(String userId);
}

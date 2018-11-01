package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository interface.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByUsername(String username);
}

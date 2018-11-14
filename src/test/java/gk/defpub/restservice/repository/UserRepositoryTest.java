package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.Role;
import gk.defpub.restservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * UserRepositoryTest class.
 * <p>
 * Date: Nov 12, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole(Role.USER);
        entityManager.persist(user);
        entityManager.flush();

        User foundUser = userRepository.findByUsername(user.getUsername());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
    }
}

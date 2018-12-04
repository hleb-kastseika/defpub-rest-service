package gk.defpub.restservice.repository;

import gk.defpub.restservice.model.Role;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.model.UserConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static gk.defpub.restservice.util.Constants.DEFAULT_CRON_SCHEDULE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserConfigRepositoryTest class.
 * <p>
 * Date: Dec 4, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserConfigRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserConfigRepository repository;

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole(Role.USER);
        entityManager.persist(user);
        UserConfig config = new UserConfig();
        config.setUserId(user.getId());
        config.setCronSchedule(DEFAULT_CRON_SCHEDULE);
        entityManager.persist(config);
        entityManager.flush();

        UserConfig foundConfig = repository.findByUserId(user.getId());
        assertThat(foundConfig).isNotNull();
    }
}

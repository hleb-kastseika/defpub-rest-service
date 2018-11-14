package gk.defpub.restservice.service;

import gk.defpub.restservice.model.User;
import gk.defpub.restservice.repository.UserRepository;
import gk.defpub.restservice.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserServiceTest class.
 * <p>
 * Date: Nov 12, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {
    private static final String TEST_USERNAME = "testUser";

    @TestConfiguration
    static class UserServiceTestConfiguration {
        @Bean
        public PasswordEncoder getEncoder() {
            return new BCryptPasswordEncoder();
        }
        @Bean
        public UserService getUserService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User();
        user.setUsername(TEST_USERNAME);

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
    }

    @Test
    public void testFindOne() {
        User foundUser = userService.findOne(TEST_USERNAME);
        assertThat(foundUser.getUsername()).isEqualTo(TEST_USERNAME);
    }
}

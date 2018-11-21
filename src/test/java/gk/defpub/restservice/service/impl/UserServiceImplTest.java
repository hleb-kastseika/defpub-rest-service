package gk.defpub.restservice.service.impl;

import gk.defpub.restservice.model.LoginUser;
import gk.defpub.restservice.model.Role;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.repository.UserRepository;
import gk.defpub.restservice.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * UserServiceImplTest class.
 * <p>
 * Date: Nov 12, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    private static final String TEST_USER_ID = "testId";
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_USERNAME_2 = "testUser2";
    private static final String TEST_PASSWORD = "test password";
    private User user;

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
        user = new User();
        user.setUsername(TEST_USERNAME);
    }

    @Test
    public void testFindOne() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        User foundUser = userService.findOne(TEST_USERNAME);
        Assertions.assertThat(foundUser.getUsername()).isEqualTo(TEST_USERNAME);
        verify(userRepository).findByUsername(TEST_USERNAME);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testSave() {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(TEST_USERNAME_2);
        loginUser.setPassword(TEST_PASSWORD);
        User user2 = new User();
        user2.setUsername(TEST_USERNAME_2);
        user2.setRole(Role.USER);
        when(userRepository.save(any(User.class))).thenReturn(user2);

        User savedUser = userService.save(loginUser);
        assertThat(savedUser.getUsername()).isEqualTo(TEST_USERNAME_2);
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        verify(userRepository).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.findAll();
        assertThat(allUsers.size()).isEqualTo(1);
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(TEST_USER_ID);
        assertThat(foundUser).isNotNull();
        verify(userRepository).findById(TEST_USER_ID);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testDelete() {
        userService.delete(TEST_USER_ID);
        verify(userRepository).deleteById(TEST_USER_ID);
        verifyNoMoreInteractions(userRepository);
    }
}
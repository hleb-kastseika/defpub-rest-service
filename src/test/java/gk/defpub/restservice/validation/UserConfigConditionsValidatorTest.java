package gk.defpub.restservice.validation;

import gk.defpub.restservice.model.User;
import gk.defpub.restservice.model.UserConfig;
import gk.defpub.restservice.service.UserConfigService;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserConfigConditionsValidatorTest class.
 * <p>
 * Date: Dec 4, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class UserConfigConditionsValidatorTest {
    private static final String TEST_CONFIG_ID = "testConfigId";
    private static final String TEST_USER_NAME = "testUserName";
    private static final String TEST_ANOTHER_USER_NAME = "testAnotherUserName";
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_ANOTHER_USER_ID = "testAnotherUserId";
    private UserConfig config = new UserConfig();
    private User user = new User();
    private User user2 = new User();
    @Mock
    private UserService userService;
    @Mock
    private UserConfigService userConigService;
    @InjectMocks
    private UserConfigConditionsValidator validator = new UserConfigConditionsValidator();

    @Before
    public void setUp() {
        initMocks(this);
        config.setId(TEST_CONFIG_ID);
        config.setUserId(TEST_USER_ID);
        user.setId(TEST_USER_ID);
        user.setUsername(TEST_USER_NAME);
        user2.setId(TEST_ANOTHER_USER_ID);
        user2.setUsername(TEST_ANOTHER_USER_NAME);
    }

    @Test
    public void testIsValid() {
        when(userConigService.findById(eq(TEST_CONFIG_ID))).thenReturn(config);
        when(userService.findOne(eq(TEST_USER_NAME))).thenReturn(user);
        Principal principal = () -> TEST_USER_NAME;
        Object[] params = {TEST_CONFIG_ID, principal};

        boolean isValid = validator.isValid(params, null);
        assertThat(isValid).isTrue();
        verify(userConigService).findById(eq(TEST_CONFIG_ID));
        verify(userService).findOne(eq(TEST_USER_NAME));
        verifyNoMoreInteractions(userConigService, userService);
    }

    @Test
    public void testIsInvalidUser() {
        when(userConigService.findById(eq(TEST_CONFIG_ID))).thenReturn(config);
        when(userService.findOne(eq(TEST_ANOTHER_USER_NAME))).thenReturn(user2);
        Principal principal = () -> TEST_ANOTHER_USER_NAME;
        Object[] params = {TEST_CONFIG_ID, principal};

        try {
            validator.isValid(params, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserPermissionException.class);
        }
        verify(userConigService).findById(eq(TEST_CONFIG_ID));
        verify(userService).findOne(eq(TEST_ANOTHER_USER_NAME));
        verifyNoMoreInteractions(userConigService, userService);
    }

    @Test
    public void testIsInvalidPublication() {
        when(userConigService.findById(eq(TEST_CONFIG_ID))).thenReturn(null);
        when(userService.findOne(eq(TEST_USER_NAME))).thenReturn(user);
        Principal principal = () -> TEST_USER_NAME;
        Object[] params = {TEST_CONFIG_ID, principal};

        try {
            validator.isValid(params, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NoSuchElementException.class);
        }
        verify(userConigService).findById(eq(TEST_CONFIG_ID));
        verifyNoMoreInteractions(userConigService);
    }
}

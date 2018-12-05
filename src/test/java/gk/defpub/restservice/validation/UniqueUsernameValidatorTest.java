package gk.defpub.restservice.validation;

import gk.defpub.restservice.model.User;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UniqueUsernameValidatorTest class.
 * <p>
 * Date: Nov 19, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class UniqueUsernameValidatorTest {
    private static final String TEST_USERNAME = "testUser";
    private User user = new User();
    @Mock
    private UserService userService;
    @InjectMocks
    private UniqueUsernameValidator validator = new UniqueUsernameValidator();

    @Before
    public void setUp() {
        initMocks(this);
        user.setUsername(TEST_USERNAME);
    }

    @Test
    public void testIsValid() {
        when(userService.findOne(eq(TEST_USERNAME))).thenReturn(null);

        boolean isValid = validator.isValid(TEST_USERNAME, null);
        assertThat(isValid).isTrue();
        verify(userService).findOne(eq(TEST_USERNAME));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testIsInvalid() {
        when(userService.findOne(eq(TEST_USERNAME))).thenReturn(user);

        boolean isValid = validator.isValid(TEST_USERNAME, null);
        assertThat(isValid).isFalse();
        verify(userService).findOne(eq(TEST_USERNAME));
        verifyNoMoreInteractions(userService);
    }
}

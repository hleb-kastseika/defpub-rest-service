package gk.defpub.restservice.validation;

import gk.defpub.restservice.model.Publication;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.service.PublicationService;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * PublicationConditionsValidatorTest class.
 * <p>
 * Date: Nov 19, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class PublicationConditionsValidatorTest {
    private static final String TEST_PUB_ID = "testPubId";
    private static final String TEST_USER_NAME = "testUserName";
    private static final String TEST_ANOTHER_USER_NAME = "testAnotherUserName";
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_ANOTHER_USER_ID = "testAnotherUserId";
    private Publication publication = new Publication();
    private User user = new User();
    private User user2 = new User();
    @MockBean
    private UserService userService;
    @MockBean
    private PublicationService publicationService;
    @InjectMocks
    private PublicationConditionsValidator validator = new PublicationConditionsValidator();

    @Before
    public void setUp() {
        initMocks(this);
        publication.setId(TEST_PUB_ID);
        publication.setUserId(TEST_USER_ID);
        user.setId(TEST_USER_ID);
        user.setUsername(TEST_USER_NAME);
        user2.setId(TEST_ANOTHER_USER_ID);
        user2.setUsername(TEST_ANOTHER_USER_NAME);

    }

    @Test
    public void testIsValid() {
        when(publicationService.findById(eq(TEST_PUB_ID))).thenReturn(publication);
        when(userService.findOne(eq(TEST_USER_NAME))).thenReturn(user);
        Principal principal = () -> TEST_USER_NAME;
        Object[] params = {TEST_PUB_ID, principal};

        boolean isValid = validator.isValid(params, null);
        assertThat(isValid).isTrue();
        verify(publicationService).findById(eq(TEST_PUB_ID));
        verify(userService).findOne(eq(TEST_USER_NAME));
        verifyNoMoreInteractions(publicationService, userService);
    }

    @Test
    public void testIsInvalidUser() {
        when(publicationService.findById(eq(TEST_PUB_ID))).thenReturn(publication);
        when(userService.findOne(eq(TEST_ANOTHER_USER_NAME))).thenReturn(user2);
        Principal principal = () -> TEST_ANOTHER_USER_NAME;
        Object[] params = {TEST_PUB_ID, principal};

        try {
            validator.isValid(params, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserPermissionException.class);
        }
        verify(publicationService).findById(eq(TEST_PUB_ID));
        verify(userService).findOne(eq(TEST_ANOTHER_USER_NAME));
        verifyNoMoreInteractions(publicationService, userService);
    }

    @Test
    public void testIsInvalidPublication() {
        when(publicationService.findById(eq(TEST_PUB_ID))).thenReturn(null);
        when(userService.findOne(eq(TEST_USER_NAME))).thenReturn(user);
        Principal principal = () -> TEST_USER_NAME;
        Object[] params = {TEST_PUB_ID, principal};

        try {
            validator.isValid(params, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NoSuchElementException.class);
        }
        verify(publicationService).findById(eq(TEST_PUB_ID));
        verifyNoMoreInteractions(publicationService);
    }
}

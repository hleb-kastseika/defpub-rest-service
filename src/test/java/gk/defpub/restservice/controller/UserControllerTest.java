package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.User;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UserControllerTest class.
 * <p>
 * Date: Nov 23, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class UserControllerTest {
    private static final String TEST_USER_ID = "testUserId";
    private User user = new User();
    private List<User> users = new ArrayList<>();
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController controller = new UserController();

    @Before
    public void setUp() {
        initMocks(this);
        user.setId(TEST_USER_ID);
        users.add(user);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.findAll()).thenReturn(users);

        List<User> returnedUsers = controller.getAllUsers();
        assertThat(returnedUsers).isNotEmpty();
        verify(userService).findAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testGetUser() {
        when(userService.findById(TEST_USER_ID)).thenReturn(user);

        User returnedUser = controller.getUser(TEST_USER_ID);
        assertThat(returnedUser).isNotNull();
        verify(userService).findById(eq(TEST_USER_ID));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUser() {
        controller.deleteUser(TEST_USER_ID);
        verify(userService).delete(eq(TEST_USER_ID));
        verifyNoMoreInteractions(userService);
    }
}

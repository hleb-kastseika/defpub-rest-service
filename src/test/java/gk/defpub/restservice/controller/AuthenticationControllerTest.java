package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.AuthToken;
import gk.defpub.restservice.model.LoginUser;
import gk.defpub.restservice.model.User;
import gk.defpub.restservice.security.TokenProvider;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * AuthenticationControllerTest class.
 * <p>
 * Date: Nov 26, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class AuthenticationControllerTest {
    private static final String TOKEN = "testToken";
    private static final String USERNAME = "testusername";
    private static final String PASSWORD = "Password!1";
    private LoginUser loginUser = new LoginUser();
    private User user = new User();
    @Mock
    private Authentication authentication;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenProvider jwtTokenUtil;
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthenticationController controller = new AuthenticationController();

    @Before
    public void setUp() {
        initMocks(this);
        loginUser.setUsername(USERNAME);
        loginUser.setPassword(PASSWORD);
        user.setUsername(USERNAME);
    }

    @Test
    public void testLogin() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtTokenUtil.generateToken(eq(authentication))).thenReturn(TOKEN);

        ResponseEntity<AuthToken> response = (ResponseEntity<AuthToken>) controller.login(loginUser);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getToken()).isEqualTo(TOKEN);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenUtil).generateToken(eq(authentication));
        verifyNoMoreInteractions(authenticationManager, jwtTokenUtil);
    }

    @Test
    public void testRegister() {
        when(userService.save(eq(loginUser))).thenReturn(user);

        ResponseEntity<Void> response = controller.register(loginUser);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(userService).save(eq(loginUser));
        verifyNoMoreInteractions(userService);
    }
}

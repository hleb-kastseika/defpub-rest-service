package gk.defpub.restservice.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static gk.defpub.restservice.util.Constants.HEADER_STRING;
import static gk.defpub.restservice.util.Constants.TOKEN_PREFIX;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * JwtAuthenticationFilterTest class.
 * <p>
 * Date: Dec 4, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class JwtAuthenticationFilterTest {
    private static final String AUTH_HEADER = "Bearer testAuthHeader";
    private static final String TEST_USERNAME = "testUser";
    private MockHttpServletRequest req = new MockHttpServletRequest();
    private HttpServletResponse res;
    @Mock
    private FilterChain chain;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private UsernamePasswordAuthenticationToken authentication;
    @Mock
    private TokenProvider jwtTokenUtil;
    @InjectMocks
    private JwtAuthenticationFilter filter;

    @Before
    public void setUp() {
        initMocks(this);
        req.addHeader(HEADER_STRING, AUTH_HEADER);
    }

    @Test
    public void testDoFilterInternal() throws IOException, ServletException {
        String authToken = AUTH_HEADER.replace(TOKEN_PREFIX, "");
        when(jwtTokenUtil.getUsernameFromToken(authToken)).thenReturn(null);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        filter.doFilterInternal(req, res, chain);
        verify(jwtTokenUtil).getUsernameFromToken(authToken);
        verifyNoMoreInteractions(jwtTokenUtil);
    }
}

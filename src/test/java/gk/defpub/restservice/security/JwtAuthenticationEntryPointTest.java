package gk.defpub.restservice.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * JwtAuthenticationEntryPointTest class.
 * <p>
 * Date: Nov 22, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class JwtAuthenticationEntryPointTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException authException;

    @InjectMocks
    private JwtAuthenticationEntryPoint entryPoint = new JwtAuthenticationEntryPoint();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testCommence() throws IOException {
        entryPoint.commence(request, response, authException);
        verify(response).sendError(eq(HttpServletResponse.SC_UNAUTHORIZED), anyString());
        verifyNoMoreInteractions(response);
    }
}

package gk.defpub.restservice.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * CORSFilterTest class.
 * <p>
 * Date: Nov 22, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class CORSFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @InjectMocks
    private CORSFilter filter = new CORSFilter();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testDoFilter() throws IOException, ServletException {
        filter.doFilter(request, response, chain);
        verify(response, times(5)).setHeader(anyString(), anyString());
        verify(chain).doFilter(request, response);
        verifyNoMoreInteractions(response, chain);
    }
}

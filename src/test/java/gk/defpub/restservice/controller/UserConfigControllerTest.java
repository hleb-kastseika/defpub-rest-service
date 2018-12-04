package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.User;
import gk.defpub.restservice.model.UserConfig;
import gk.defpub.restservice.service.UserConfigService;
import gk.defpub.restservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static gk.defpub.restservice.util.Constants.DEFAULT_CRON_SCHEDULE;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserConfigControllerTest class.
 * <p>
 * Date: Dec 4, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@RunWith(SpringRunner.class)
public class UserConfigControllerTest {
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_CONFIG_ID = "testConfigId";
    private static final String TEST_USERNAME = "testUsername";
    private Principal principal;
    private UserConfig userConfig = new UserConfig();
    private List<UserConfig> userConfigs = new ArrayList<>();
    @Mock
    private UserConfigService userConfigService;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserConfigController controller = new UserConfigController();

    @Before
    public void setUp() {
        initMocks(this);
        userConfig.setUserId(TEST_USER_ID);
        userConfig.setId(TEST_CONFIG_ID);
        userConfig.setCronSchedule(DEFAULT_CRON_SCHEDULE);
        userConfigs.add(userConfig);
        principal = new Principal() {
            @Override
            public boolean equals(Object another) {
                return false;
            }

            @Override
            public String toString() {
                return getName();
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return TEST_USERNAME;
            }
        };
    }

    @Test
    public void testGetAllUserConfigs() {
        when(userConfigService.findAll()).thenReturn(userConfigs);

        ResponseEntity<?> response = controller.getUserConfigs(null, principal);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((List<UserConfig>) response.getBody()).isNotEmpty();
        verify(userConfigService).findAll();
        verifyNoMoreInteractions(userConfigService);
    }

    @Test
    public void testGetSpecificUserConfig() {
        when(userConfigService.findByUserId(eq(TEST_USER_ID))).thenReturn(userConfig);

        ResponseEntity<?> response = controller.getUserConfigs(TEST_USER_ID, principal);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((UserConfig) response.getBody()).isNotNull();
        verify(userConfigService).findByUserId(TEST_USER_ID);
        verifyNoMoreInteractions(userConfigService);
    }

    @Test
    public void testGetCurrentUserConfig() {
        User user = new User();
        user.setId(TEST_USER_ID);
        user.setUsername(TEST_USERNAME);
        when(userService.findOne(eq(TEST_USERNAME))).thenReturn(user);
        when(userConfigService.findByUserId(eq(TEST_USER_ID))).thenReturn(userConfig);

        UserConfig returnedUserConfig = controller.getUserConfig(principal);
        assertThat(returnedUserConfig).isNotNull();
        assertThat(returnedUserConfig.getId()).isEqualTo(userConfig.getId());
        verify(userService).findOne(TEST_USERNAME);
        verify(userConfigService).findByUserId(TEST_USER_ID);
        verifyNoMoreInteractions(userService, userConfigService);
    }

    @Test
    public void testUpdateUserConfig() {
        when(userConfigService.save(eq(userConfig))).thenReturn(userConfig);

        UserConfig returnedUserConfig = controller.updateUserConfig(TEST_CONFIG_ID, principal, userConfig);
        assertThat(returnedUserConfig).isNotNull();
        verify(userConfigService).save(userConfig);
        verifyNoMoreInteractions(userConfigService);
    }
}

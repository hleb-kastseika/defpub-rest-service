package gk.defpub.restservice.service.impl;

import gk.defpub.restservice.model.UserConfig;
import gk.defpub.restservice.repository.UserConfigRepository;
import gk.defpub.restservice.service.UserConfigService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static gk.defpub.restservice.util.Constants.DEFAULT_CRON_SCHEDULE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * UserConfigServiceImplTest class.
 * <p>
 * Date: Dec 4, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class UserConfigServiceImplTest {
    private static final String TEST_USER_ID = "testUserId";
    private static final String TEST_CONFIG_ID = "testConfigId";
    private UserConfig userConfig = new UserConfig();
    private List<UserConfig> userConfigs = new ArrayList<>();
    @Mock
    private UserConfigRepository userConfigRepository;
    @InjectMocks
    private UserConfigService service = new UserConfigServiceImpl();

    @Before
    public void setUp() {
        initMocks(this);
        userConfig.setId(TEST_CONFIG_ID);
        userConfig.setUserId(TEST_USER_ID);
        userConfig.setCronSchedule(DEFAULT_CRON_SCHEDULE);

        userConfigs.add(userConfig);
    }

    @Test
    public void testSave() {
        UserConfig changedConfig = new UserConfig();
        changedConfig.setId(TEST_CONFIG_ID);
        changedConfig.setUserId(TEST_USER_ID);
        changedConfig.setCronSchedule("0 0 2-7 * * *");
        when(userConfigRepository.findById(eq(TEST_CONFIG_ID))).thenReturn(Optional.of(userConfig));
        when(userConfigRepository.save(userConfig)).thenReturn(changedConfig);

        UserConfig savedConfig = service.save(changedConfig);
        assertThat(savedConfig).isNotNull();
        verify(userConfigRepository).findById(eq(TEST_CONFIG_ID));
        verify(userConfigRepository).save(userConfig);
        verifyNoMoreInteractions(userConfigRepository);
    }

    @Test
    public void testFindAll() {
        when(userConfigRepository.findAll()).thenReturn(userConfigs);

        List<UserConfig> configs = service.findAll();
        assertThat(configs).isNotEmpty();
        verify(userConfigRepository).findAll();
        verifyNoMoreInteractions(userConfigRepository);
    }

    @Test
    public void testFindByUserId() {
        when(userConfigRepository.findByUserId(TEST_USER_ID)).thenReturn(userConfig);

        UserConfig config = service.findByUserId(TEST_USER_ID);
        assertThat(config).isNotNull();
        verify(userConfigRepository).findByUserId(TEST_USER_ID);
        verifyNoMoreInteractions(userConfigRepository);
    }

    @Test
    public void testFindById() {
        when(userConfigRepository.findById(TEST_CONFIG_ID)).thenReturn(Optional.of(userConfig));

        UserConfig config = service.findById(TEST_CONFIG_ID);
        assertThat(config).isNotNull();
        verify(userConfigRepository).findById(TEST_CONFIG_ID);
        verifyNoMoreInteractions(userConfigRepository);
    }

    @Test
    public void testDelete() {
        service.delete(TEST_CONFIG_ID);
        verify(userConfigRepository).deleteById(TEST_CONFIG_ID);
        verifyNoMoreInteractions(userConfigRepository);
    }
}

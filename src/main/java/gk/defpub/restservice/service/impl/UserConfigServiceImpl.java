package gk.defpub.restservice.service.impl;

import gk.defpub.restservice.model.UserConfig;
import gk.defpub.restservice.repository.UserConfigRepository;
import gk.defpub.restservice.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserConfigServiceImpl class.
 * <p>
 * Date: Dec 3, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Service(value = "userConfigService")
public class UserConfigServiceImpl implements UserConfigService {
    @Autowired
    private UserConfigRepository userConfigRepository;

    @Override
    public UserConfig save(UserConfig config) {
        UserConfig oldUserConfig = userConfigRepository.findById(config.getId()).get();
        oldUserConfig.setCronSchedule(config.getCronSchedule());
        return userConfigRepository.save(oldUserConfig);
    }

    @Override
    public List<UserConfig> findAll() {
        return (List) userConfigRepository.findAll();
    }

    @Override
    public UserConfig findByUserId(String userId) {
        return userConfigRepository.findByUserId(userId);
    }

    @Override
    public UserConfig findById(String id) {
        return userConfigRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        userConfigRepository.deleteById(id);
    }
}

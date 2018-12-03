package gk.defpub.restservice.controller;

import gk.defpub.restservice.model.UserConfig;
import gk.defpub.restservice.service.UserConfigService;
import gk.defpub.restservice.service.UserService;
import gk.defpub.restservice.validation.UserConfigValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;

/**
 * PublicationController class.
 * <p>
 * Date: Dec 3, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/configs")
@Validated
public class UserConfigController {
    @Autowired
    private UserConfigService userConfigService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUserConfigs(@RequestParam(value = "userid", required = false) String userId,
                                            Principal principal) {
        return ResponseEntity.ok(StringUtils.isEmpty(userId) ? userConfigService.findAll() : userConfigService.findByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public UserConfig getUserConfig(Principal principal) {
        return userConfigService.findByUserId(userService.findOne(principal.getName()).getId());
    }

    @UserConfigValid
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserConfig updateUserConfig(@NotBlank @PathVariable(value = "id") String id,
                                       Principal principal,
                                       @Valid @RequestBody UserConfig config) {
        return userConfigService.save(config);
    }
}

package gk.defpub.restservice.validation;

import gk.defpub.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * UniqueUsernameValidator class.
 * <p>
 * Date: Nov 14, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.findOne(value) == null;
    }
}

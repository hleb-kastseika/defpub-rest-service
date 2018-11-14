package gk.defpub.restservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UniqueUsername annotation.
 * <p>
 * Date: Nov 14, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueUsername {

    String message() default "This username is already used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

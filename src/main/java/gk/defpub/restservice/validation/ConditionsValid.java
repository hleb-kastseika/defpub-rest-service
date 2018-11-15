package gk.defpub.restservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ConditionsValid annotation.
 * <p>
 * Date: Nov 15, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Constraint(validatedBy = PublicationConditionsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ConditionsValid {

    String message() default "Some conditions of the action are not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

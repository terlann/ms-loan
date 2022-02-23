package az.kapitalbank.loan.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "^\\+994[0-9]{9}$", message = "Phone regex is not true")
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface Phone {
    String message() default "Phone regex exception";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.StringLengthValidator;

@Documented
@Constraint(validatedBy = StringLengthValidator.class)
@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface StringLength {
	int min() default 0;

	int max() default Integer.MAX_VALUE;

	String message() default "{validator.constraints.StringLength}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
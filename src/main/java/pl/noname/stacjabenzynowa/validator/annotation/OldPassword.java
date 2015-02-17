package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.OldPasswordValidator;

@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldPasswordValidator.class)
@Documented
public @interface OldPassword {
	String message() default "{validator.constraints.OldPassword}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldEncryptedPassword();

	String fieldPassword();

	/**
	 * @see OldPassword
	 */
	@Target( { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		OldPassword[] value();
	}
}


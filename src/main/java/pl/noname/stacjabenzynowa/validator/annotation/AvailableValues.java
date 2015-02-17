package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.AvailableValuesValidator;

@Target( { ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AvailableValuesValidator.class)
@Documented
public @interface AvailableValues {
	String message() default "{validator.constraints.AvailableValues}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

    String[] value() default {};
    
    String[] valueList() default {};
}

package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.RegonValidator;

@Target( { ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegonValidator.class)
@Documented
public @interface Regon {
	String message() default "{validator.constraints.Regon}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.AlphaValidator;


@Target( { ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlphaValidator.class)
@Documented
public @interface Alpha 
{
	String message() default "{validator.constraints.Alpha}";    
	
	boolean allowWhiteSpace() default false;
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}

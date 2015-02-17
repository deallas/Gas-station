package pl.noname.stacjabenzynowa.validator.annotation;
 
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.BigDecimalRangeValidator;
 
/**
 * The annotated element has to be in the appropriate range. Apply on BigDecimal values.
 */
@Target( { ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BigDecimalRangeValidator.class)
@Documented
public @interface BigDecimalRange {
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    long minPrecision() default Long.MIN_VALUE;
    long maxPrecision() default Long.MAX_VALUE;
    int scale() default 0;
 
    String message() default "{validator.constraints.BigDecimalRange}";
}

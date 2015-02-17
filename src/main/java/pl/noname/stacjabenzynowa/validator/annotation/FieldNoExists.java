package pl.noname.stacjabenzynowa.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.noname.stacjabenzynowa.validator.FieldNoExistsValidator;

@Constraint(validatedBy={FieldNoExistsValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldNoExists {

	Class<?> tableName();
	
    String[] columnNames();

    String assignResultToField() default "";
    
    String message() default "{validator.constraints.FieldNoExists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldNoExists[] value();
    }
}
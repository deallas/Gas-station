package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.Alpha;

public class AlphaValidator implements ConstraintValidator<Alpha, Object> 
{
	private boolean allowWhiteSpace;
	
	/* ----------------------------------------------------------- */
	
	public boolean isAllowWhiteSpace() {
		return allowWhiteSpace;
	}

	public void setAllowWhiteSpace(boolean allowWhiteSpace) {
		this.allowWhiteSpace = allowWhiteSpace;
	}
	
	/* ----------------------------------------------------------- */
	
	@Override
	public void initialize(Alpha constraintAnnotation) {
		allowWhiteSpace = constraintAnnotation.allowWhiteSpace();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext contex) 
	{
		String whiteSpace = allowWhiteSpace ? "| " : "";
        String pattern = "(\\p{L}" + whiteSpace + ")*";
		
		return ((String)value).matches(pattern);
	}
}

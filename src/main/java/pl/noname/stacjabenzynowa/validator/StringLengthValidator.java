package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.StringLength;

public class StringLengthValidator implements ConstraintValidator<StringLength, String> 
{
	private int min;
	private int max;

	public void initialize(StringLength parameters) 
	{
		min = parameters.min();
		max = parameters.max();
		validateParameters();
	}

	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) 
	{
		if ( value == null || value.isEmpty() ) {
			return true;
		}
		int length = value.length();
		return length >= min && length <= max;
	}

	private void validateParameters() 
	{
		if ( min < 0 ) {
			throw new IllegalArgumentException("The min parameter cannot be negative.");
		}
		if ( max < 0 ) {
			throw new IllegalArgumentException("The max parameter cannot be negative.");
		}
		if ( max < min ) {
			throw new IllegalArgumentException("The length cannot be negative.");
		}
	}
}
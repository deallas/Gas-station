package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.Regon;

public class RegonValidator implements ConstraintValidator<Regon, Object>  {

	@Override
	public void initialize(Regon constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String regon = (String)value;
		if(regon == null || regon.isEmpty()){
			return false;
		}
		regon = trimInput(regon);
        int rsize = regon.length();
        if (!((rsize == 9) || (rsize == 14))) {
            return false;
        }
        int[] weights = null;
        if(rsize==9){
        	int[] wag = {8,9,2,3,4,5,6,7};
        	weights = wag;
        }
        else{
        	int[] wag = {2,4,8,5,0,9,7,3,6,1,2,4,8};
        	weights = wag;
        }
        int j = 0, sum = 0, control = 0;
        long csum = new Long(regon.substring(rsize - 1)).intValue();
        for (int i = 0; i < rsize - 1; i++) {
            char c = regon.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = sum % 11;
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
	}
	
	private static String trimInput(String input) {
        return input.replaceAll("\\D*","");
    }
}

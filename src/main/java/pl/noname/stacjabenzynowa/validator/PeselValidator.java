package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.Pesel;


public class PeselValidator implements ConstraintValidator<Pesel, Object> {

	@Override
	public void initialize(Pesel arg0) {
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext contex) {
		String pesel = (String)value;
		if(pesel == null || pesel.isEmpty()){
			return false;
		}
		pesel= trimInput(pesel);
		int psize=pesel.length();
		if(psize!=11){
			return false;
		}
		else{
			int[] weights = {1,3,7,9,1,3,7,9,1,3};
	        int j = 0, sum = 0, control = 0;
	        int csum = new Integer(pesel.substring(psize - 1)).intValue();
	        for (int i = 0; i < psize - 1; i++) {
	            char c = pesel.charAt(i);
	            j = new Integer(String.valueOf(c)).intValue();
	            sum += j * weights[i];
	        }
	        control = 10 - (sum % 10);
	        if (control == 10) {
	            control = 0;
	        }
	        return (control == csum);
		}
	}
	
	private static String trimInput(String input) {
        return input.replaceAll("\\D*","");
    }
}

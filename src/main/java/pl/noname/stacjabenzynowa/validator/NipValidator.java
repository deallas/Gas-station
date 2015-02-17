package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.Nip;

public class NipValidator implements ConstraintValidator<Nip, Object> {

	@Override
	public void initialize(Nip constraintAnnotation) {

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String nip = (String)value;
		if(nip == null || nip.isEmpty()){
			return true;
		}
		nip = trimInput(nip);
        int nsize = nip.length();
        if (nsize != 10) {
            return false;
        }
        int[] weights = {6,5,7,2,3,4,5,6,7};
        int j = 0, sum = 0, control = 0;
        int csum = new Integer(nip.substring(nsize - 1)).intValue();
        if (csum == 0) {
            csum = 10;
        }
        for (int i = 0; i < nsize - 1; i++) {
            char c = nip.charAt(i);
            j = new Integer(String.valueOf(c)).intValue();
            sum += j * weights[i];
        }
        control = sum % 11;
        return (control == csum);
	}

	private static String trimInput(String input) {
		return input.replaceAll("\\D*","");
	}
}

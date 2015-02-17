package pl.noname.stacjabenzynowa.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;
 
/**
 * The value has to be in a defined range with precision &amp; scale
 */
public class BigDecimalRangeValidator implements ConstraintValidator<BigDecimalRange, Object> {;
 
    private long minPrecision;
    private long maxPrecision;
    private int scale;
 
    public void initialize(BigDecimalRange parameters) {
        minPrecision = parameters.minPrecision();
        maxPrecision = parameters.maxPrecision();
        scale = parameters.scale();
    }
 
    public boolean isValid(Object value, ConstraintValidatorContext valContext) 
    {
    	BigDecimal bd;
    	
        if (value == null) {
        	return false;
        } else if(value instanceof String) {
        	String vS = (String)value;
        	if(vS.isEmpty())
        	{
        		return false;
        	}
        	bd = new BigDecimal(vS);
        } else if(value instanceof Float) {
        	bd = new BigDecimal(Float.toString((Float)value));
        } else if(value instanceof Double) {
        	bd = new BigDecimal(Double.toString((Double)value));
        } else if(value instanceof Integer) {
        	bd = new BigDecimal((Integer)value);
        } else if(value instanceof Long) {
        	bd = new BigDecimal((Long)value);
        } else if(value instanceof BigDecimal) {
        	bd = (BigDecimal)value;
        } else {
        	throw new NumberFormatException();
        }
        
        int _precision = bd.precision();
        int _scale = bd.scale();
        return (_precision >= minPrecision && _precision <= maxPrecision && _scale <= scale);
    }
}
package pl.noname.stacjabenzynowa.validator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

public class AvailableValuesValidator implements ConstraintValidator<AvailableValues, Object> 
{
	@Autowired 
	private ApplicationContext applicationContext;
	
	private String[] values;
	private String[] valueList;
	private static final Logger logger = LoggerFactory.getLogger(AvailableValuesValidator.class);
	
	@Override
	public void initialize(AvailableValues constraintAnnotation) {
		values = constraintAnnotation.value();
		valueList = constraintAnnotation.valueList();
	}	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) 
	{
		if(value == null) {
			return false;
		}
		if(values != null){
			for(String v : values){
				if(((String)value).equals(v)){
					return true;
				}
			}
		}
		logger.debug(Arrays.toString(valueList));
		
		if(valueList != null && valueList.length == 3){
			String objClassMethod = valueList[0];
			String objServiceBean = valueList[1];
			String objServiceMethod = valueList[2];
			
			Object serviceObj = null;
			Method serviceMethod = null, classMethod = null;
			List<?> list = null;
			
			serviceObj = applicationContext.getBean(objServiceBean);
			try {
				serviceMethod = serviceObj.getClass().getDeclaredMethod(objServiceMethod);
			} catch (Exception e) {
				logger.error(e.toString());
			}
			try {
				list = (List<?>) serviceMethod.invoke(serviceObj,new Object[] {});
			} catch (Exception e) {
				logger.error(e.toString());
			}			
			try {
				if(list.isEmpty()){
					return false;
				}
				classMethod = list.get(0).getClass().getDeclaredMethod(objClassMethod);
			} catch (Exception e) {
				logger.error(e.toString());
			}
			
			for(Object obj : list){
				String str = null;
				try {
					str = String.valueOf(classMethod.invoke(obj,new Object[] {}));
				} catch (Exception e) {
					logger.error(e.toString());
				}
				if((String.valueOf(value)).equals(str)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}

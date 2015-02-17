package pl.noname.stacjabenzynowa.validator;

import java.util.List;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pl.noname.stacjabenzynowa.validator.annotation.FieldNoExists;

public class FieldNoExistsValidator extends SessionFactoryAwareValidator implements ConstraintValidator<FieldNoExists, Object> {

    private Class<?> className;
    
    private String[] columnNames;
    
    private String assignResultToField;
    
    @Override
    public void initialize(FieldNoExists constraintAnnotation) {
        this.columnNames = constraintAnnotation.columnNames();
        this.className = constraintAnnotation.tableName();
        this.assignResultToField = constraintAnnotation.assignResultToField();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
    	Session sess = getCurrentSession();
    	Criteria criteria = sess.createCriteria(className);
    	
    	try {
	    	for(String columnName : columnNames) {
	    		PropertyDescriptor desc = new PropertyDescriptor(columnName, target.getClass());
	            Method readMethod = desc.getReadMethod();
	            Object propertyValue = readMethod.invoke(target);
	    		criteria.add(Restrictions.eq(columnName, propertyValue));
	    	}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    	List<?> result = criteria.list();
    	if(result.size() == 0)
    		return false;
    
    	if(!assignResultToField.isEmpty()) {
	    	try {
	    		BeanUtils.setProperty(target, assignResultToField, result.get(0));
		    } catch (Exception ex) {
				ex.printStackTrace();
			}
    	}
    	return true;
    }
}
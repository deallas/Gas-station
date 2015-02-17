package pl.noname.stacjabenzynowa.validator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;

public class FieldExistsValidator extends SessionFactoryAwareValidator implements ConstraintValidator<FieldExists, Object> {

    private Class<?> className;
    
    private String[] columnNames;
    
    private String exclude;
    
    @Override
    public void initialize(FieldExists constraintAnnotation) {
        this.columnNames = constraintAnnotation.columnNames();
        this.className = constraintAnnotation.tableName();
        this.exclude = constraintAnnotation.exclude();
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
	            if(String.valueOf(propertyValue).isEmpty()){
	            	return true;
	            }
	    		criteria.add(Restrictions.eq(columnName, propertyValue));
	    		if(!exclude.isEmpty()){
	    			final Object excludeId = BeanUtils.getProperty(target, exclude);
	    			criteria.add(Restrictions.ne("id",Integer.valueOf((String) excludeId)));
	    		}
	    	}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    	
    	return criteria.list().size() == 0;
    }
}
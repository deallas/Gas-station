package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.validator.annotation.OldPassword;

public class OldPasswordValidator implements ConstraintValidator<OldPassword, Object> 
{
	@Autowired
	private AclService aclService;
	
	private String fieldPassword;
	private String fieldEncryptedPassword;
	
	@Override
	public void initialize(OldPassword constraintAnnotation) 
	{
		fieldEncryptedPassword = constraintAnnotation.fieldEncryptedPassword();
		fieldPassword = constraintAnnotation.fieldPassword();
	}	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) 
	{		
		try {
			final String encryptedPassword = BeanUtils.getProperty(value, fieldEncryptedPassword);
			final String password = BeanUtils.getProperty(value, fieldPassword);

			return aclService.checkOldPassword(encryptedPassword, password);
		} catch(final Exception ignore) {
			//ignore
		}
		
		return false;
	}

	public AclService getAclService() {
		return aclService;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}
}

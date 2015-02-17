package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.validator.annotation.OldPassword;
import pl.noname.stacjabenzynowa.validator.OldPasswordValidator;
import pl.noname.stacjabenzynowa.web.form.PasswordEmployeeEditForm;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class OldPasswordValidatorTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private OldPasswordValidator validator;
	private OldPassword aValues;
	private ConstraintValidatorContext context;
	private PasswordEmployeeEditForm form;
	
	@Autowired
	private AclService aclService;
	
	@Before
	public void setUp()
	{
		validator = new OldPasswordValidator();
		validator.setAclService(aclService);
		
		form = new PasswordEmployeeEditForm();
		aValues = EasyMock.createMock(OldPassword.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		expect(aValues.fieldEncryptedPassword()).andReturn("encryptedPassword");
		expect(aValues.fieldPassword()).andReturn("oldPassword");
		replay(aValues);
		validator.initialize(aValues);
	}
	
	@Test
	public void testValid()
	{	
		form.setEncryptedPassword("$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy");
		form.setOldPassword("12345");
		assertTrue(validator.isValid(form, context));
	}	
	
	@Test
	public void testInvalid()
	{	
		form.setEncryptedPassword("$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy");
		form.setOldPassword("54321");
		assertFalse(validator.isValid(form, context));
	}	
}

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
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.FieldMatchValidator;
import pl.noname.stacjabenzynowa.web.form.AccountEmployeeEditForm;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class FieldMatchValidatorTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private FieldMatchValidator validator;
	private FieldMatch aValues;
	private ConstraintValidatorContext context;
	private AccountEmployeeEditForm form;
	
	@Autowired
	private AclService aclService;
	
	@Before
	public void setUp()
	{
		validator = new FieldMatchValidator();
		
		form = new AccountEmployeeEditForm();
		aValues = EasyMock.createMock(FieldMatch.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		expect(aValues.first()).andReturn("email");
		expect(aValues.second()).andReturn("confirmEmail");
		replay(aValues);
		validator.initialize(aValues);
	}
	
	@Test
	public void testValid()
	{	
		form.setEmail("test@test.pl");
		form.setConfirmEmail("test@test.pl");
		assertTrue(validator.isValid(form, context));
	}	
	
	@Test
	public void testInvalid()
	{	
		form.setEmail("test@test.pl");
		form.setConfirmEmail("test2@test.pl");
		assertFalse(validator.isValid(form, context));
	}	
}

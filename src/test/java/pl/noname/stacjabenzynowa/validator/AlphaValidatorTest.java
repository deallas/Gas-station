package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;

import org.easymock.EasyMock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.validator.annotation.Alpha;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class AlphaValidatorTest extends AbstractTransactionalJUnit4SpringContextTests{

	private AlphaValidator validator;
	
	private Alpha alpha;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new AlphaValidator();
		alpha = EasyMock.createMock(Alpha.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		validator.initialize(alpha);
	}
	
	@Test
	public void testIsValid() //test poprawności - znaki z zakresu [a-zA-Z]
	{	
		assertTrue(validator.isValid("QWERTIOPASDFGHKLZXCVBNMqwetyiopasdfghklzxcvbnm", context));
	}
	
	@Test
	public void testIsValid2() //test poprawności - polskie znaki
	{	
		assertTrue(validator.isValid("żąćęźółŻĄĆĘŹÓŁ", context));
	}
	
	@Test
	public void testIsValid3() //test poprawności - spacje
	{	
		validator.setAllowWhiteSpace(true);
		assertTrue(validator.isValid("Marian z Bliskiej", context));
	}
	
	@Test
	public void testIsValid4() //test poprawności - puste
	{	
		assertTrue(validator.isValid("", context));
	}
	
	@Test
	public void testIsInvalid1() //test niepoprawności - spacje
	{	
		assertFalse(validator.isValid("Michal z Sanoka", context));
	}
	
	@Test
	public void testIsInvalid3() //test niepoprawności - cyfry
	{	
		assertFalse(validator.isValid("qwerty1234567890", context));
	}
	
	@Test
	public void testIsInvalid4() //test niepoprawności - znaki specjalne
	{	
		assertFalse(validator.isValid("qwerty!@#$%^&*(){}:\"<>?\\`~", context));
	}	
}

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

import pl.noname.stacjabenzynowa.validator.annotation.Pesel;


@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class PeselValidatorTest extends AbstractTransactionalJUnit4SpringContextTests{

	private PeselValidator validator;
	
	private Pesel pesel;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new PeselValidator();
		pesel = EasyMock.createMock(Pesel.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		validator.initialize(pesel);
	}
	
	@Test
	public void testIsValid() //test poprawności
	{	
		assertTrue(validator.isValid("91052401710", context));
	}
	
	@Test
	public void testIsValid2() //test poprawnosći usuwania znaków
	{	
		assertTrue(validator.isValid("9 $#10 &*52401710", context));
	}
	
	@Test
	public void testIsInvalid1() //test niepoprawności długości
	{	
		assertFalse(validator.isValid("1111111111", context));
	}
	
	@Test
	public void testIsInvalid2() //test niepoprawności
	{	
		assertFalse(validator.isValid("11111111111", context));
	}
	
	@Test
	public void testIsInvalid3() //test niepoprawności znaków
	{	
		assertFalse(validator.isValid("abcdefghijk", context));
	}
}

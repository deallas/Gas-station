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

import pl.noname.stacjabenzynowa.validator.annotation.Nip;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class NipValidatorTest extends AbstractTransactionalJUnit4SpringContextTests{

	private NipValidator validator;
	
	private Nip nip;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new NipValidator();
		nip = EasyMock.createMock(Nip.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		validator.initialize(nip);
	}
	
	@Test
	public void testIsValid() //test poprawności
	{	
		assertTrue(validator.isValid("9492134738", context));
	}
	
	@Test
	public void testIsValid2() //test poprawności usuwania znaków
	{	
		assertTrue(validator.isValid("949-213-4738", context));
	}
	
	@Test
	public void testIsValid3() //test poprawności usuwania znaków
	{	
		assertTrue(validator.isValid("9#$49-213-4  73**8", context));
	}
	
	@Test
	public void testIsInvalid1() //test nieopowiedniej długości znaków
	{	
		assertFalse(validator.isValid("111111111111", context));
	}
	
	@Test
	public void testIsInvalid2() //test niepoprawnego NIPu
	{	
		assertFalse(validator.isValid("1112223333", context));
	}
	
	@Test
	public void testIsInvalid3() //test niepoprawnego NIPu
	{	
		assertFalse(validator.isValid("abcdefghij", context));
	}
}

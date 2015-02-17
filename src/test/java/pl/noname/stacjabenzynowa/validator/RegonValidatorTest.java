package pl.noname.stacjabenzynowa.validator;

import static org.junit.Assert.*;

import javax.validation.ConstraintValidatorContext;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.validator.annotation.Regon;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class RegonValidatorTest extends AbstractTransactionalJUnit4SpringContextTests {

private RegonValidator validator;
	
	private Regon regon;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new RegonValidator();
		regon = EasyMock.createMock(Regon.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		validator.initialize(regon);
	}
	
	@Test
	public void testIsValidLength9() {
		assertTrue(validator.isValid("732065814", context));
	}
	
	@Test
	public void testIsValidLength14() {
		assertTrue(validator.isValid("23511332857188", context));
	}
	
	@Test
	public void testIsValid1() {
		assertTrue(validator.isValid("235-11-3328-571-88", context));
	}
	
	@Test
	public void testIsValid2() {
		assertTrue(validator.isValid("235-^&11-33$#@28-57$%1-%88", context));
	}
	
	@Test
	public void testIsInValid1() {
		assertFalse(validator.isValid("732065814456", context));
	}
	
	@Test
	public void testIsInValid2() {
		assertFalse(validator.isValid("fsdgsd3425hsd", context));
	}
	
	@Test
	public void testIsInValid3() {
		assertFalse(validator.isValid("732065567", context));
	}
	
	@Test
	public void testIsInValid4() {
		assertFalse(validator.isValid("23511332857199", context));
	}
}

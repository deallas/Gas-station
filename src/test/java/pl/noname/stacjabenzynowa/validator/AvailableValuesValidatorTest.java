package pl.noname.stacjabenzynowa.validator;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class AvailableValuesValidatorTest extends AbstractTransactionalJUnit4SpringContextTests{

	private AvailableValuesValidator validator;
	
	private AvailableValues aValues;
	
	private String[] enums;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new AvailableValuesValidator();
		aValues = EasyMock.createMock(AvailableValues.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		enums = new String[3];
		enums[0]="MALE";
		enums[1]="FEMALE";
		enums[2]="UNKNOWN";
		expect(aValues.valueList()).andReturn(null);
		expect(aValues.value()).andReturn(enums);
		replay(aValues);
		//verify(aValues);
		//verify(context);
		validator.initialize(aValues);
	}
	
	@Test
	public void testIsValid() //test poprawności wartości
	{	
		assertTrue(validator.isValid("UNKNOWN", context));
	}
	
	@Test
	public void testIsInvalid() //test niepoprawnosci wartości
	{	
		assertFalse(validator.isValid("MALES", context));
	}
}

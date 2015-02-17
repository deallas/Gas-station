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

import pl.noname.stacjabenzynowa.validator.annotation.StringLength;
import pl.noname.stacjabenzynowa.validator.StringLengthValidator;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class StringLengthValidatorTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private StringLengthValidator validator;
	
	private StringLength stringLength;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp()
	{
		validator = new StringLengthValidator();
		stringLength = EasyMock.createMock(StringLength.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
	}

	@Test
	public void testValid1()
	{	
		expect(stringLength.min()).andReturn(3);
		expect(stringLength.max()).andReturn(5);
		replay(stringLength);
		validator.initialize(stringLength);
		
		assertFalse(validator.isValid("as", context));
		assertTrue(validator.isValid("asd", context));
		assertTrue(validator.isValid("asdf", context));
		assertTrue(validator.isValid("asdfg", context));
		assertFalse(validator.isValid("asdfgh", context));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid1()
	{
		expect(stringLength.min()).andReturn(5);
		expect(stringLength.max()).andReturn(3);
		replay(stringLength);
		validator.initialize(stringLength);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid2()
	{
		expect(stringLength.min()).andReturn(-1);
		expect(stringLength.max()).andReturn(3);
		replay(stringLength);
		validator.initialize(stringLength);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid3()
	{
		expect(stringLength.min()).andReturn(5);
		expect(stringLength.max()).andReturn(-1);
		replay(stringLength);
		validator.initialize(stringLength);
	}
}

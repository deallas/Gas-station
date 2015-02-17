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

import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;
import pl.noname.stacjabenzynowa.validator.BigDecimalRangeValidator;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class BigDecimalRangeTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private BigDecimalRangeValidator validator;
	
	private BigDecimalRange bigDecimalRange;
	
	ConstraintValidatorContext context;
	
	@Before
	public void setUp(){
		validator = new BigDecimalRangeValidator();
		bigDecimalRange = EasyMock.createMock(BigDecimalRange.class);
		context = EasyMock.createMock(ConstraintValidatorContext.class);
		expect(bigDecimalRange.minPrecision()).andReturn(0L);
		expect(bigDecimalRange.maxPrecision()).andReturn(5L);
		expect(bigDecimalRange.scale()).andReturn(2);
		replay(bigDecimalRange);
		
		validator.initialize(bigDecimalRange);
	}
	
	@Test
	public void testValid1()
	{	
		assertTrue(validator.isValid("999.99", context));
	}
	
	@Test
	public void testValid2()
	{
		assertTrue(validator.isValid(999, context));
	}
	
	@Test
	public void testValid3()
	{
		assertTrue(validator.isValid(999L, context));
	}
	
	@Test
	public void testValid4()
	{
		assertTrue(validator.isValid(999.99, context));
	}
	
	@Test
	public void testValid5()
	{
		assertTrue(validator.isValid(999.99d, context));
	}
	
	@Test
	public void testValid6()
	{	
		assertTrue(validator.isValid(-999.99, context));
	}
	
	@Test
	public void testInvalid1()
	{
		assertFalse(validator.isValid("999.999", context));
	}
	
	@Test
	public void testInvalid2()
	{
		assertFalse(validator.isValid("9999.99", context));
	}
	
	@Test
	public void testInvalid3()
	{
		assertFalse(validator.isValid("9999.999", context));
	}
	
	@Test
	public void testInvalid4()
	{
		assertFalse(validator.isValid("", context));
	}
	
	@Test
	public void testInvalid5()
	{
		assertFalse(validator.isValid(null, context));
	}
	
	@Test(expected = NumberFormatException.class)
	public void testInvalid6()
	{
		validator.isValid("999i", context);
	}
	
	@Test
	public void testInvalid7()
	{	
		assertFalse(validator.isValid(-999.999, context));
	}
	
	@Test
	public void testInvalid8()
	{	
		assertFalse(validator.isValid(-9999.99, context));
	}
	
	@Test(expected = NumberFormatException.class)
	public void testInvalid9()
	{
		validator.isValid(new Object(), context);
	}
}

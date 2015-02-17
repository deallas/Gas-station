package pl.noname.stacjabenzynowa.security;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import javax.annotation.Resource;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class EmployeeAuthenticationProviderTest
{
	@Resource(name="employeeAuthenticationProvider")
	private EmployeeAuthenticationProvider employeeAuthenticationProvider;

	private Authentication authentication;
	private WebAuthenticationDetails details;
	
	/* ----------------------------------------------------------- */
	
	public EmployeeAuthenticationProvider getEmployeeAuthenticationProvider() {
		return employeeAuthenticationProvider;
	}

	public void setAuthenticationProvider(
			EmployeeAuthenticationProvider employeeAuthenticationProvider) {
		this.employeeAuthenticationProvider = employeeAuthenticationProvider;
	}
	
	/* ----------------------------------------------------------- */
	
	@Before
	public void setup()
	{
		authentication = EasyMock.createMock(Authentication.class);
		details = EasyMock.createMock(WebAuthenticationDetails.class);
		expect(details.getRemoteAddress()).andReturn("127.0.0.1");
		replay(details);
		expect(authentication.getDetails()).andReturn(details);
	}
	
	@Test
	public void successAuthenticationTest()
	{
		expect(authentication.getPrincipal()).andReturn("test@test.pl").anyTimes();
		expect(authentication.getCredentials()).andReturn("12345").anyTimes();
		replay(authentication);
		assertTrue(employeeAuthenticationProvider.authenticate(authentication) instanceof Authentication);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void badPasswordTest()
	{
		expect(authentication.getPrincipal()).andReturn("test@test.pl").anyTimes();
		expect(authentication.getCredentials()).andReturn("54321").anyTimes();
		replay(authentication);
		employeeAuthenticationProvider.authenticate(authentication);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void notIssetUsernameTest()
	{
		expect(authentication.getPrincipal()).andReturn("nieistniejacy@test.pl").anyTimes();
		expect(authentication.getCredentials()).andReturn("12345").anyTimes();
		replay(authentication);
		employeeAuthenticationProvider.authenticate(authentication);
		
	}
}

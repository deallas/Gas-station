package pl.noname.stacjabenzynowa.security;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.noname.stacjabenzynowa.security.EmployeeDetailsService;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class EmployeeDetailsServiceTest 
{
	@Resource(name="employeeDetailsService")
	private EmployeeDetailsService employeeDetailsService;
		
	/* ----------------------------------------------------------- */
	
	public EmployeeDetailsService getEmployeeDetailsService() {
		return employeeDetailsService;
	}

	public void setEmployeeDetailsService(EmployeeDetailsService employeeDetailsService) {
		this.employeeDetailsService = employeeDetailsService;
	}

	/* ----------------------------------------------------------- */
	
	@Test
	public void getSuccessUserByEmailTest() 
	{
		String username = "test@test.pl";
	
		assertTrue(employeeDetailsService.loadUserByUsername(username) instanceof UserDetails);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void usernameNotFoundTest()
	{
		String username = "nieistnieje@test.pl";
		
		employeeDetailsService.loadUserByUsername(username);
	}
	
	@Test
	public void getSuccessUserByPeselTest()
	{
		String username = "49040501580";
	
		assertTrue(employeeDetailsService.loadUserByUsername(username) instanceof UserDetails);
	}
}

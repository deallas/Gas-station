package pl.noname.stacjabenzynowa.service;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.dao.EmployeeDao;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class EmployeeServiceTest
{
	@Resource(name="employeeService")
	private EmployeeService employeeService;

	@Resource(name="aclService")
	private AclService aclService;
	
	/* ----------------------------------------------------------- */
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public AclService getAclService() {
		return aclService;
	}
	
	public void setAclService (AclService aclService) {
		this.aclService = aclService;
	}
	
	/* ----------------------------------------------------------- */
	
	@Test
	public void testGetEmployeeByPeselOrEmail()
	{
		Employee em, em2;
		
		String pesel = "49040501580";
		
		em = employeeService.getEmployeeByPeselOrEmail(pesel);
		
		assertNotNull("Nie znaleziono pracownika na podstawie numeru PESEL", em);
		
		String email = "test@test.pl";
		
		em2 = employeeService.getEmployeeByPeselOrEmail(email);
		
		assertNotNull("Nie znaleziono pracownika na podstawie adresu email", em2);
	}

	@Test
	@Transactional
	public void testCreate()
	{	
		EmployeeDao employeeDao = employeeService.getEmployeeDao();
		Integer i = employeeDao.findCount();
		AclRole role = aclService.getRoleByName("EMP");
		Employee em = new Employee("Jan", "Nowak", "91012233119", "5693100632",
				Employee.Gender.MALE, "testCreate@test.pl", "604604604", "234234", 
				Employee.Status.ACTIVE, "Londyn", null, role);
		
		employeeService.createEmployee(em);
		
		i++;
		assertEquals("Nie dodano pracownika",i,employeeDao.findCount());
	}
}

package pl.noname.stacjabenzynowa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.EmployeeService;

@Service("employeeDetailsService")
public class EmployeeDetailsService implements UserDetailsService
{
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Assembler assemblerService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setAssemblerService(Assembler assemblerService) {
		this.assemblerService = assemblerService;
	}

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Employee employee = employeeService.getEmployeeByPeselOrEmail(username);

		return assemblerService.buildUserFromUserEntity(employee);
	}

}

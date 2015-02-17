package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.dao.EmployeeDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.EmployeeOptions;

public interface EmployeeService 
{
	public void createEmployee(Employee employee);	
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);	
	public HibernatePaginator<Employee> getPaginatorEmployees(Integer pageNumber, String order, Boolean ascing);
	public EmployeeDao getEmployeeDao();
	public EmployeeOptions getEmployeePaginator();
	public Employee getEmployeeByPeselOrEmail(String username);
	public Employee getEmployeeById(Integer id);
	public List<Employee> getEmployeesByRole(AclRole role);
	public void editPassword(Employee employee, String password);
}

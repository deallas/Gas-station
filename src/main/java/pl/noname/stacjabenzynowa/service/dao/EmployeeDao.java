package pl.noname.stacjabenzynowa.service.dao;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;

public interface EmployeeDao extends GenericDao<Employee,Integer>
{
	public Employee getEmployeeByPeselOrEmail(String value);
	public HibernatePaginator<Employee> getPaginatorEmployees(AbstractOptions options);
	public Employee getEmployeeById(Integer id);
}

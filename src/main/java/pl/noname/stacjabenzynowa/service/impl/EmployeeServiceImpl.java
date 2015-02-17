package pl.noname.stacjabenzynowa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.persistance.Employee;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.service.dao.EmployeeDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.web.paginator.EmployeeOptions;

@Service("employeeService")
@Transactional(propagation=Propagation.REQUIRED)
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	private AclService aclService;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmployeeOptions employeePaginator;
	
	/* ----------------------------------------------------------- */
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public EmployeeOptions getEmployeePaginator() {
		return employeePaginator;
	}

	public void setEmployeePaginator(EmployeeOptions employeePaginator) {
		this.employeePaginator = employeePaginator;
	}
	
	/* ----------------------------------------------------------- */

	@Override
	public void createEmployee(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeDao.delete(employee);
	}
	
	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<Employee> getPaginatorEmployees(Integer pageNumber, String order, Boolean ascing)
	{
		AbstractOptions options = employeePaginator.getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = employeePaginator.setPaginatorOptions(options);
		
		HibernatePaginator<Employee> paginator = employeeDao.getPaginatorEmployees(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public Employee getEmployeeByPeselOrEmail(String username) 
	{
		if(username.isEmpty()){
			throw new UsernameNotFoundException("exception.username.Empty");
		}
		Criterion crit = Restrictions.or(
				Restrictions.eq("email", username),
				Restrictions.eq("pesel",username)
		);
		
		Employee employee;
		List<Employee> emps = employeeDao.findByCriteria(crit);
		if(emps.isEmpty()) {
			throw new UsernameNotFoundException("exception.username.NotFound");
		} else {
			employee = emps.get(0);
		}
		
		return employee;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Employee getEmployeeById(Integer id){
		return employeeDao.getEmployeeById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Employee> getEmployeesByRole(AclRole role) {		
		Criterion crit = Restrictions.eq("role", role);
        return employeeDao.findByCriteria(crit);
	}
	
	@Override
	@Transactional
	public void editPassword(Employee employee, String password) 
	{
	    employee.setPassword(aclService.encodePassword(password));
		this.updateEmployee(employee);
	}
}

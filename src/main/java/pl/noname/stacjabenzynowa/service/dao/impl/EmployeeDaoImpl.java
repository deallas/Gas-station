package pl.noname.stacjabenzynowa.service.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.dao.EmployeeDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;

@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee,Integer> implements EmployeeDao 
{	
	@Override
	public Employee getEmployeeByPeselOrEmail(String value) 
	{
		Criterion crit = Restrictions.or(
				Restrictions.eq("pesel", value),
				Restrictions.eq("email", value)
			);

		Employee em = findByCriteria(crit).get(0);
		
		return em;
	}
	
	@Override
	public HibernatePaginator<Employee> getPaginatorEmployees(AbstractOptions options)
	{
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.createAlias("role", "r");
		
		HibernatePaginator<Employee> hp = new HibernatePaginator<Employee>(criteria, options);
		
		return hp;
	}
	
	@Override
	public Employee getEmployeeById(Integer id) {
		return findById(id);
	}
}

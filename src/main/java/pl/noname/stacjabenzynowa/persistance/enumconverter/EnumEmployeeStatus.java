package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.Employee;

public class EnumEmployeeStatus extends PersistentEnumType<Employee.Status>
{
	@Override
	public Class<Employee.Status> returnedClass() {
		return Employee.Status.class;
	}
}
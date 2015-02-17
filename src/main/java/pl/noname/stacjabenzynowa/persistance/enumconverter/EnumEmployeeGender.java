package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.Employee;

public class EnumEmployeeGender extends PersistentEnumType<Employee.Gender>
{
	@Override
	public Class<Employee.Gender> returnedClass() {
		return Employee.Gender.class;
	}
}
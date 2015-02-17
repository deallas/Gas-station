package pl.noname.stacjabenzynowa.web.form;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.persistance.Employee.Gender;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.annotation.StringLength;

@FieldMatch.List({
	@FieldMatch(first="password",
				second="confirmPassword",
				message="{form.confirmPassword}"
	) 
})
@FieldExists.List({
	@FieldExists(columnNames={"pesel"}, tableName=Employee.class, exclude="id", message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Employee.class, exclude="id", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Employee.class, exclude="id", message="{form.email.FieldNoExists}")
})
public class EmployeeEditForm extends AbstractManagedEmployeeForm 
{
	private int id;

	@StringLength(min=5, message="{form.password.Length}")
	private String password;
	
	private String confirmPassword;

	/* ----------------------------------------------------------- */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/* ----------------------------------------------------------- */

	public Employee toEmployee() {
		Employee e = super.toEmployee();
		e.setId(id);
		e.setPassword(password);
		
		return e;
	}
	
	public void populateByEmployee(Employee emp) {
		setId(emp.getId());
		setName(emp.getName());
		setSurname(emp.getSurname());
		Gender g = emp.getGender();
		if(g == null) {
			setGender(Gender.UNKNOWN.toString());
		} else {
			setGender(emp.getGender().toString());
		}
		setPesel(emp.getPesel());
		setNip(emp.getNip());
		setPhone(emp.getPhoneNumber());
		setAddress(emp.getAddress());
		setEmail(emp.getEmail());
		setConfirmEmail(emp.getEmail());
		setRoleId(emp.getRole().getId().longValue());
		setStatus(emp.getStatus().toString());
	}
}

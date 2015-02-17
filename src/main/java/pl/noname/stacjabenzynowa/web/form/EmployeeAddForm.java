package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first="password",
				second="confirmPassword",
				message="{form.confirmPassword}"
	) 
})
@FieldExists.List({
	@FieldExists(columnNames={"pesel"}, tableName=Employee.class, message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Employee.class, message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Employee.class, message="{form.email.FieldNoExists}")
})
public class EmployeeAddForm extends AbstractManagedEmployeeForm 
{
	@Length(min=5, message="{form.password.Length}")
	@NotBlank
	private String password;
	
	@NotBlank
	private String confirmPassword;

	/* ----------------------------------------------------------- */

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
		e.setPassword(password);
		
		return e;
	}
}

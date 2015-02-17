package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.annotation.OldPassword;

@FieldMatch.List({
	@FieldMatch(first="password",
				second="confirmPassword",
				message="{form.confirmPassword}"
	) 
})
@OldPassword(fieldPassword = "oldPassword", 
			 fieldEncryptedPassword = "encryptedPassword"
)
public class PasswordEmployeeEditForm 
{
	private String encryptedPassword;
	
	@NotBlank
	@Length(min=5, message="{form.password.Length}")
	private String password;
	
	private String confirmPassword;
	
	private String oldPassword;

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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void populateByEmployee(Employee employee)
	{
		encryptedPassword = employee.getPassword();
	}
}

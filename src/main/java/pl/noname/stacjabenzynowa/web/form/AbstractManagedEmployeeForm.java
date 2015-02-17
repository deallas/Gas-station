package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

@FieldMatch.List({
	@FieldMatch(first="email",
				second="confirmEmail",
				message="{form.confirmEmail}"
	),
	@FieldMatch(first="password",
				second="confirmPassword",
				message="{form.confirmPassword}"
	) 
})
public abstract class AbstractManagedEmployeeForm extends AbstractEmployeeForm 
{

	private AclRole[] roles;

	@Length(min=5, max=100, message="{form.email.Length}")
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Email
	private String confirmEmail;
	
	@NotBlank
	@AvailableValues({"ACTIVE", "INACTIVE","BANNED"})
	private String status;
	
	@AvailableValues(valueList={"getId","aclService","getRoles"})
	private long roleId;
	
	private AclRole role;

	/* ----------------------------------------------------------- */
	
	public AclRole getRole() {
		return role;
	}

	public void setRole(AclRole role) {
		this.role = role;
	}

	public AclRole[] getRoles() {
		return roles;
	}

	public void setRoles(AclRole[] roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/* ----------------------------------------------------------- */
	
	public Employee toEmployee() {
		Employee e = super.toEmployee();
		e.setRole(role);
		e.setStatus(Employee.Status.valueOf(status));
		e.setEmail(email);
		
		return e;
	}
}

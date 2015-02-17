package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.persistance.Employee.Gender;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first="email",
				second="confirmEmail",
				message="{form.confirmEmail}"
	) 
})

@FieldExists.List({
	@FieldExists(columnNames={"pesel"}, tableName=Employee.class, exclude="id", message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Employee.class, exclude="id", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Employee.class, exclude="id", message="{form.email.FieldNoExists}")
})
public class AccountEmployeeEditForm extends AbstractEmployeeForm {
	
	private int id;
	
	@Length(min=5, max=100, message="{form.email.Length}")
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Email
	private String confirmEmail;
	
	private Employee employee;

	/* ----------------------------------------------------------- */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/* ----------------------------------------------------------- */
	


	public Employee toEmployee() {
		if(employee == null){
			employee = new Employee();
		}
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPesel(pesel);
		if(!(nip == null || nip.isEmpty())){
			employee.setNip(nip.trim().replaceAll("-", ""));
		}
		if(!(phone == null || phone.isEmpty())){
			if(phone.length()==9){
				employee.setPhoneNumber(phone);
			}else if(phone.length()==15){
				String tmpPhone = String.valueOf(phone.subSequence(3, phone.length()));
				employee.setPhoneNumber(tmpPhone.trim().replaceAll("-", ""));
			}
		}
		employee.setAddress(address);
		employee.setGender(Employee.Gender.valueOf(gender));
		
		return employee;
	}
	
	public void populateByEmployee(Employee emp){
		this.employee = emp;
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
	}
}

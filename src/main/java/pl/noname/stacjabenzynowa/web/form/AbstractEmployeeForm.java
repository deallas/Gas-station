package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.validator.annotation.Alpha;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.Nip;
import pl.noname.stacjabenzynowa.validator.annotation.Pesel;

public abstract class AbstractEmployeeForm 
{
	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	@Alpha
	protected String name;
	
	@NotBlank
	@Length(min=1, max=50, message="{form.surname.Length}")
	@Alpha
	protected String surname;
	
	@NotBlank
	@Pesel
	protected String pesel;
	
	@Nip
	protected String nip;

	@NotBlank
	@AvailableValues({"MALE","FEMALE","UNKNOWN"})
	protected String gender;
	
	@Pattern(regexp="(^$|(([+][0-9]{2}\\s)?[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{3}))", message="{form.phone.Wrong}")
	protected String phone;
	
	@Length(max=150, message="{form.address.Length}")
	protected String address;

	/* ----------------------------------------------------------- */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/* ----------------------------------------------------------- */
	
	public Employee toEmployee() {
		Employee e = new Employee();
		e.setName(name);
		e.setSurname(surname);
		e.setPesel(pesel);
		if(!(nip == null || nip.isEmpty())){
			e.setNip(nip.trim().replaceAll("-", ""));
		}
		if(!(phone == null || phone.isEmpty())){
			if(phone.length()==9) {
				e.setPhoneNumber(phone);
			} else if(phone.length()==15) {
				String tmpPhone = String.valueOf(phone.subSequence(3, phone.length()));
				e.setPhoneNumber(tmpPhone.trim().replaceAll("-", ""));
			}
		}
		e.setAddress(address);
		e.setGender(Employee.Gender.valueOf(gender));
		
		return e;
	}
}

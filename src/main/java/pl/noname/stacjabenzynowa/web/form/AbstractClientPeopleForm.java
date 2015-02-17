package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.validator.annotation.Alpha;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.Pesel;

public abstract class AbstractClientPeopleForm extends AbstractClientForm
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

	@NotBlank
	@AvailableValues({"MALE","FEMALE","UNKNOWN"})
	protected String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/* ----------------------------------------------------------- */
	
	public ClientPeople toClientPeople() {
		ClientPeople c = new ClientPeople();
		c.setName(name);
		c.setSurname(surname);
		c.setPesel(pesel);
		c.setGender(ClientPeople.Gender.valueOf(gender));
		
		return c;
	}
}

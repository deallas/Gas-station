package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.validator.annotation.Nip;

public abstract class AbstractClientForm 
{	
	@Nip
	protected String nip;
	
	@Pattern(regexp="(^$|(([+][0-9]{2}\\s)?[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{3}))", message="{form.phone.Wrong}")
	protected String phone;
	
	@Length(max=150, message="{form.address.Length}")
	protected String address;
	
	@Length(min=5, max=100, message="{form.email.Length}")
	@NotBlank
	@Email
	protected String email;
	
	@NotBlank
	@Email
	protected String confirmEmail;

	/* ----------------------------------------------------------- */
	
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
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

	/* ----------------------------------------------------------- */
	
	public Client toClient() {
		Client c = new Client();
		if(!(nip == null || nip.isEmpty())){
			c.setNip(nip.trim().replaceAll("-", ""));
		}
		if(!(phone == null || phone.isEmpty())){
			if(phone.length()==9){
				c.setPhoneNumber(phone);
			}else if(phone.length()==15){
				String tmpPhone = String.valueOf(phone.subSequence(3, phone.length()));
				c.setPhoneNumber(tmpPhone.trim().replaceAll("-", ""));
			}
		}
		c.setAddress(address);
		c.setEmail(email);
		
		return c;
	}
}

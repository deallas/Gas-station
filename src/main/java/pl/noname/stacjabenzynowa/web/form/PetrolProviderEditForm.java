package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.persistance.PetrolProvider.Status;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.annotation.Nip;
import pl.noname.stacjabenzynowa.validator.annotation.Regon;

@FieldMatch.List({
	@FieldMatch(first="email",
	second="confirmEmail",
	message="{form.confirmEmail}"
	) 
})

@FieldExists.List({
	@FieldExists(columnNames={"regon"}, tableName=PetrolProvider.class, exclude="id", message="{form.regon.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=PetrolProvider.class, exclude="id", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=PetrolProvider.class, exclude="id", message="{form.email.FieldNoExists}")
})
public class PetrolProviderEditForm {
	
	private int id;
	
	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	protected String companyName;
	
	@Nip
	protected String nip;
	
	@Regon
	protected String regon;
	
	@Length(max=150, message="{form.address.Length}")
	protected String address;

	@NotBlank
	@AvailableValues({"ACTIVE","INACTIVE"})
	protected String status;
	
	@Pattern(regexp="(^$|(([+][0-9]{2}\\s)?[0-9]{3}[-]?[0-9]{3}[-]?[0-9]{3}))", message="{form.phone.Wrong}")
	protected String phone;
	
	@Length(min=5, max=100, message="{form.email.Length}")
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Email
	private String confirmEmail;

	/* ----------------------------------------------------------- */
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getRegon() {
		return regon;
	}

	public void setRegon(String regon) {
		this.regon = regon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
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
	
	/* ----------------------------------------------------------- */


	public PetrolProvider toPetrolProvider() {
		PetrolProvider provider = new PetrolProvider();

		provider.setId(id);
		provider.setCompanyName(companyName);
		if(!(nip == null || nip.isEmpty())){
			provider.setNip(nip.trim().replaceAll("-", ""));
		}
		if(!(regon == null || regon.isEmpty())){
			provider.setRegon(regon.trim().replaceAll("-", ""));
		}
		if(!(phone == null || phone.isEmpty())){
			if(phone.length()==9) {
				provider.setPhoneNumber(phone);
			} else if(phone.length()==15) {
				String tmpPhone = String.valueOf(phone.subSequence(3, phone.length()));
				provider.setPhoneNumber(tmpPhone.trim().replaceAll("-", ""));
			}
		}
		provider.setAddress(address);
		provider.setStatus(PetrolProvider.Status.valueOf(status));
		provider.setEmail(email);
		
		return provider;
	}
	
	public void populateByPetrolProvider(PetrolProvider prov) {
		
		setId(prov.getId());
		setCompanyName(prov.getCompanyName());
		setRegon(prov.getRegon());
		setNip(prov.getNip());
		Status s = prov.getStatus();
		if(s == null) {
			setStatus(Status.INACTIVE.toString());
		} else {
			setStatus(prov.getStatus().toString());
		}
		setPhone(prov.getPhoneNumber());
		setAddress(prov.getAddress());
		setEmail(prov.getEmail());
		setConfirmEmail(prov.getEmail());
	}
	
}

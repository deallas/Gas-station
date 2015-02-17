package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;
import pl.noname.stacjabenzynowa.validator.annotation.StringLength;

@FieldMatch.List({
	@FieldMatch(first="password",
				second="confirmPassword",
				message="{form.confirmPassword}"
	),
	@FieldMatch(first="email",
	second="confirmEmail",
	message="{form.confirmEmail}"
	) 
})
@FieldExists.List({
	@FieldExists(columnNames={"regon"}, tableName=ClientCompany.class, message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Client.class, message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Client.class, message="{form.email.FieldNoExists}")
})

public class ClientCompanyAddForm extends AbstractClientCompanyForm {

	@NotBlank
	@StringLength(min=5, message="{form.password.Length}")
	private String password;
	
	private String confirmPassword;
	
	@NotBlank
	@AvailableValues({"ACTIVE", "INACTIVE"})
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Client toClient(){
		Client c = super.toClient();
		c.setPassword(password);
		c.setStatus(Client.Status.valueOf(status));
		c.setPoints(0);
		
		return c;
	}
	
	public ClientCompany toClientCompany(){
		ClientCompany cc = super.toClientCompany();

		return cc;
	}

}

package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.NotNull;

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
	@FieldExists(columnNames={"regon"}, tableName=ClientCompany.class, exclude="clientCompanyId", message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Client.class, exclude="clientId", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Client.class, exclude="clientId", message="{form.email.FieldNoExists}")
})

public class ClientCompanyEditForm extends AbstractClientCompanyForm {
	
	private int clientId;
	
	private int clientCompanyId;

	@StringLength(min=5, message="{form.password.Length}")
	private String password;
	
	private String confirmPassword;
	
	@NotBlank
	@AvailableValues({"ACTIVE", "INACTIVE","BANNED"})
	private String status;
	
	@NotNull
	private Integer points;

	private Client client;
	
	private ClientCompany clientCompany;
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientCompanyId() {
		return clientCompanyId;
	}

	public void setClientCompanyId(int clientCompanyId) {
		this.clientCompanyId = clientCompanyId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientCompany getClientCompany() {
		return clientCompany;
	}

	public void setClientCompany(ClientCompany clientCompany) {
		this.clientCompany = clientCompany;
	}

	public Client toClient(){
		Client c = super.toClient();
		c.setId(clientId);
		c.setPassword(password);
		c.setStatus(Client.Status.valueOf(status));
		c.setPoints(points);
		c.setDateAdded(client.getDateAdded());
		c.setCity(client.getCity());
		c.setRole(client.getRole());
		return c;
	}
	
	public ClientCompany toClientCompany(){
		ClientCompany cc = super.toClientCompany();
		cc.setId(clientCompanyId);
		cc.setClient(toClient());
		return cc;
	}
	
	public void populateByClientCompany(ClientCompany cc) {
		this.client = cc.getClient();
		setClientId(client.getId());
		this.clientCompany = cc;
		setClientCompanyId(cc.getId());
		setCompanyName(cc.getCompanyName());
		setRegon(cc.getRegon());
		setNip(client.getNip());
		setPhone(client.getPhoneNumber());
		setAddress(client.getAddress());
		setEmail(client.getEmail());
		setConfirmEmail(client.getEmail());
		setStatus(cc.getClient().getStatus().toString());
		setPoints(cc.getClient().getPoints());
	}

}

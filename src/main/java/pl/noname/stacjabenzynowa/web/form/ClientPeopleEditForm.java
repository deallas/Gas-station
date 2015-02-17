package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.persistance.ClientPeople.Gender;
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
	@FieldExists(columnNames={"pesel"}, tableName=ClientPeople.class, exclude="clientPeopleId", message="{form.pesel.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Client.class, exclude="clientId", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Client.class, exclude="clientId", message="{form.email.FieldNoExists}")
})

public class ClientPeopleEditForm extends AbstractClientPeopleForm {
	
	private int clientId;
	
	private int clientPeopleId;

	@StringLength(min=5, message="{form.password.Length}")
	private String password;
	
	private String confirmPassword;
	
	@NotBlank
	@AvailableValues({"ACTIVE", "INACTIVE","BANNED"})
	private String status;
	
	@NotNull
	private Integer points;

	private Client client;
	
	private ClientPeople clientPeople;
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getClientPeopleId() {
		return clientPeopleId;
	}

	public void setClientPeopleId(int clientPeopleId) {
		this.clientPeopleId = clientPeopleId;
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

	public ClientPeople getClientPeople() {
		return clientPeople;
	}

	public void setClientPeople(ClientPeople clientPeople) {
		this.clientPeople = clientPeople;
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
	
	public ClientPeople toClientPeople(){
		ClientPeople cp = super.toClientPeople();
		cp.setId(clientPeopleId);
		cp.setClient(toClient());
		return cp;
	}
	
	public void populateByClientPeople(ClientPeople cp) {
		this.setClient(cp.getClient());
		setClientId(getClient().getId());
		this.setClientPeople(cp);
		setClientPeopleId(cp.getId());
		setName(cp.getName());
		setSurname(cp.getSurname());
		Gender g = cp.getGender();
		if(g == null) {
			setGender(Gender.UNKNOWN.toString());
		} else {
			setGender(cp.getGender().toString());
		}
		setStatus(cp.getClient().getStatus().toString());
		setPoints(cp.getClient().getPoints());
		setPesel(cp.getPesel());
		setNip(getClient().getNip());
		setPhone(getClient().getPhoneNumber());
		setAddress(getClient().getAddress());
		setEmail(getClient().getEmail());
		setConfirmEmail(getClient().getEmail());
	}

}

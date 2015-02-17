package pl.noname.stacjabenzynowa.web.form;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.persistance.ClientPeople.Gender;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;

@FieldMatch.List({
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
public class AccountClientPeopleEditForm extends AbstractClientPeopleForm {

	private int clientId;

	private int clientPeopleId; 

	private Client client;
	private ClientPeople clientPeople;
	
	/* ----------------------------------------------------------- */
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}

	public ClientPeople getClientPople(){
		return clientPeople;
	}
	
	public void setClientPeople(ClientPeople clientPeople){
		this.clientPeople = clientPeople;
	}
	
	public int getClientId(){
		return clientId;
	}
	
	public void setClientId(int clientId){
		this.clientId = clientId;
	}
	
	public int getClientPeopleId(){
		return clientPeopleId;
	}
	
	public void setClientPeopleId(int clientPeopleId){
		this.clientPeopleId = clientPeopleId;
	}
	
	/* ----------------------------------------------------------- */
	
	public ClientPeople toClientPeople(){
		if(clientPeople == null){
			clientPeople = new ClientPeople();
		}
		clientPeople.setGender(ClientPeople.Gender.valueOf(gender));
		clientPeople.setName(name);
		clientPeople.setSurname(surname);
		clientPeople.setPesel(pesel);
		
		if(client == null){
			client = new Client();
		}
		if(!(nip == null || nip.isEmpty())){
			client.setNip(nip.trim().replaceAll("-", ""));
		}
		if(!(phone == null || phone.isEmpty())){
			if(phone.length()==9){
				client.setPhoneNumber(phone);
			}else if(phone.length()==15){
				String tmpPhone = String.valueOf(phone.subSequence(3, phone.length()));
				client.setPhoneNumber(tmpPhone.trim().replaceAll("-", ""));
			}
		}
		client.setAddress(address);
		client.setEmail(email);
		
		clientPeople.setClient(client);
		
		return clientPeople;
	}
	
	public void populateByClientPeople(ClientPeople cp) {
		this.client = cp.getClient();
		setClientId(client.getId());
		this.clientPeople = cp;
		setClientPeopleId(cp.getId());
		setName(cp.getName());
		setSurname(cp.getSurname());
		Gender g = cp.getGender();
		if(g == null) {
			setGender(Gender.UNKNOWN.toString());
		} else {
			setGender(cp.getGender().toString());
		}
		setPesel(cp.getPesel());
		setNip(client.getNip());
		setPhone(client.getPhoneNumber());
		setAddress(client.getAddress());
		setEmail(client.getEmail());
		setConfirmEmail(client.getEmail());
	}
}

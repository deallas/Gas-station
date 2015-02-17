package pl.noname.stacjabenzynowa.web.form;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.validator.annotation.FieldExists;
import pl.noname.stacjabenzynowa.validator.annotation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first="email",
				second="confirmEmail",
				message="{form.confirmEmail}"
	) 
})

@FieldExists.List({
	@FieldExists(columnNames={"regon"}, tableName=ClientCompany.class, exclude="clientCompanyId", message="{form.regon.FieldNoExists}"),
	@FieldExists(columnNames={"nip"}, tableName=Client.class, exclude="clientId", message="{form.nip.FieldNoExists}"),
	@FieldExists(columnNames={"email"}, tableName=Client.class, exclude="clientId", message="{form.email.FieldNoExists}")
})
public class AccountClientCompanyEditForm extends AbstractClientCompanyForm {

	private int clientId;

	private int clientCompanyId; 

	private Client client;
	private ClientCompany clientCompany;
	
	/* ----------------------------------------------------------- */
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}

	public ClientCompany getClientCompany(){
		return clientCompany;
	}
	
	public void setClientCompany(ClientCompany clientCompany){
		this.clientCompany = clientCompany;
	}
	
	public int getClientId(){
		return clientId;
	}
	
	public void setClientId(int clientId){
		this.clientId = clientId;
	}
	
	public int getClientCompanyId(){
		return clientCompanyId;
	}
	
	public void setClientCompanyId(int clientCompanyId){
		this.clientCompanyId = clientCompanyId;
	}
	
	/* ----------------------------------------------------------- */
	
	public ClientCompany toClientCompany(){
		if(clientCompany == null){
			clientCompany = new ClientCompany();
		}
		clientCompany.setCompanyName(companyName);
		clientCompany.setRegon(regon);
		
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
		
		clientCompany.setClient(client);
		
		return clientCompany;
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
	}
}

package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.service.dao.ClientCompanyDao;
import pl.noname.stacjabenzynowa.service.dao.ClientDao;
import pl.noname.stacjabenzynowa.service.dao.ClientPeopleDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface ClientService 
{
	public void createClient(Client client);	
	public void updateClient(Client client);
	public void deleteClient(Client client);	
	public ClientDao getClientDao();
	public Client getClientByEmail(String username);
	public Client getClientById(Integer id);
	public List<Client> getClients();
	
	/* Client People */
	public void createClientPeople(ClientPeople clientPeople);	
	public void updateClientPeople(ClientPeople clientPeople);
	public void deleteClientPeople(ClientPeople clientPeople);
	public HibernatePaginator<ClientPeople> getPaginatorClientPeople(Integer pageNumber, String order, Boolean ascing);
	public ClientPeopleDao getClientPeopleDao();
	public ClientPeople getClientPeopleByClient(Client client);
	
	
	/* Client Companies */
	public void createClientCompany(ClientCompany clientCompany);	
	public void updateClientCompany(ClientCompany clientCompany);
	public void deleteClientCompany(ClientCompany clientCompany);
	public HibernatePaginator<ClientCompany> getPaginatorClientCompany(Integer pageNumber, String order, Boolean ascing);
	public ClientCompanyDao getClientCompanyDao();
	public ClientCompany getClientCompanyByClient(Client client);
	
}

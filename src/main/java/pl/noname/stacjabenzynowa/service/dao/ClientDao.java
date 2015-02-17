package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface ClientDao extends GenericDao<Client,Integer>
{
	public Client getClientByEmail(String value);
	public HibernatePaginator<Client> getPaginatorClients(Integer pageNumber, Integer items);
	public Client getClientById(Integer id);
	public List<Client> getClients();
}

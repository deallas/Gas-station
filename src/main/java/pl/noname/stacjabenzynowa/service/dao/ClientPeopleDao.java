package pl.noname.stacjabenzynowa.service.dao;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface ClientPeopleDao extends GenericDao<ClientPeople,Integer> {
	public ClientPeople getClientPeopleByClient(Client client);
	public HibernatePaginator<ClientPeople> getPaginatorClientPeople(AbstractOptions options);
}

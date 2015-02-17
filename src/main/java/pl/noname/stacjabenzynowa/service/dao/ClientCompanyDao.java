package pl.noname.stacjabenzynowa.service.dao;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface ClientCompanyDao extends GenericDao<ClientCompany,Integer>
{
	public ClientCompany getClientCompanyByClient(Client client);
	public HibernatePaginator<ClientCompany> getPaginatorClientCompany(AbstractOptions options);
}

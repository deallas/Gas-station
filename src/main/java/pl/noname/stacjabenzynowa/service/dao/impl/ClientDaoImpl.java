package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.service.dao.ClientDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("clientDao")
public class ClientDaoImpl extends GenericDaoImpl<Client,Integer> implements ClientDao 
{
	@Override
	public Client getClientByEmail(String value) 
	{
		Criterion crit = Restrictions.or(
				Restrictions.eq("email", value)
			);

		Client cl = findByCriteria(crit).get(0);
		
		return cl;
	}
	
	@Override
	public HibernatePaginator<Client> getPaginatorClients(Integer pageNumber, Integer items)
	{
		Criteria criteria = getSession().createCriteria(Client.class);
		criteria.setFetchMode("role", FetchMode.JOIN);
		
		HibernatePaginator<Client> hp = new HibernatePaginator<Client>(criteria);
		hp.setCurrentPageNumber(pageNumber);
		hp.setItemCountPerPage(items);
		
		return hp;
	}
	
	@Override
	public Client getClientById(Integer id)
	{
		Client cl = findById(id);

		return cl;
	}

	@Override
	public List<Client> getClients() {
		return findAll();
	}
}

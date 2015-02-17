package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.service.dao.ClientPeopleDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("clientPeopleDao")
public class ClientPeopleDaoImpl extends GenericDaoImpl<ClientPeople,Integer> implements ClientPeopleDao 
{
	@Override
	public ClientPeople getClientPeopleByClient(Client client) 
	{
		if(client != null){
			Criterion crit = Restrictions.eq("client", client);
			List<ClientPeople> cp = findByCriteria(crit);
			if(cp.size() > 0){
				return cp.get(0);
			}
			return null;
		}
		return null;
	}
	
	@Override
	public HibernatePaginator<ClientPeople> getPaginatorClientPeople(AbstractOptions options)
	{
		Criteria criteria = getSession().createCriteria(ClientPeople.class);
		criteria.setFetchMode("client", FetchMode.JOIN);
		criteria.createAlias("client", "c");
		
		HibernatePaginator<ClientPeople> hp = new HibernatePaginator<ClientPeople>(criteria,options);
		
		return hp;
	}
}

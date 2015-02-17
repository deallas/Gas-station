package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.service.dao.ClientCompanyDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("clientCompanyDao")
public class ClientCompanyDaoImpl extends GenericDaoImpl<ClientCompany,Integer> implements ClientCompanyDao 
{
	@Override
	public ClientCompany getClientCompanyByClient(Client client) 
	{
		if(client != null){
			Criterion crit = Restrictions.eq("client", client);
			List<ClientCompany> cc = findByCriteria(crit);
			if(cc.size() > 0){
				return cc.get(0);
			}
			return null;
		}
		return null;
	}
	
	@Override
	public HibernatePaginator<ClientCompany> getPaginatorClientCompany(AbstractOptions options)
	{
		Criteria criteria = getSession().createCriteria(ClientCompany.class);
		criteria.setFetchMode("client", FetchMode.JOIN);
		criteria.createAlias("client", "c");
		
		HibernatePaginator<ClientCompany> hp = new HibernatePaginator<ClientCompany>(criteria,options);
		
		return hp;
	}
}

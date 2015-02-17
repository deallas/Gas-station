package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.service.dao.AclResgroupDao;

@Repository("aclResgroupDao")
public class AclResgroupDaoImpl extends GenericDaoImpl<AclResgroup,Integer> 
	implements AclResgroupDao {
	
	public AclResgroup getResgroupByName(String name)
	{
		Criterion crit = Restrictions.and(
			Restrictions.eq("name", name)	
		);
		List<AclResgroup> groups = findByCriteria(crit);
		if(!groups.isEmpty()){
			return groups.get(0);
		}
		return null;
	}
}

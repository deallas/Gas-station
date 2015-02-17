package pl.noname.stacjabenzynowa.service.dao;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;

public interface AclResgroupDao extends GenericDao<AclResgroup,Integer> 
{
	public AclResgroup getResgroupByName(String name);
}

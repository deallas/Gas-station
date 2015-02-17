package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.service.dao.AclRoleDao;

@Repository("aclRoleDao")
public class AclRoleDaoImpl extends GenericDaoImpl<AclRole,Integer> implements AclRoleDao  {

	public AclRole getRoleById(Integer id){
		Criterion crit = Restrictions.and(
				Restrictions.eq("id", id)	
			);
			List<AclRole> roles = findByCriteria(crit);
			if(!roles.isEmpty()){
				return roles.get(0);
			}
			return null;
	}
	
	public AclRole getRoleByName(String name) {
		Criterion crit = Restrictions.and(
				Restrictions.eq("name", name)	
			);
			List<AclRole> roles = findByCriteria(crit);
			if(!roles.isEmpty()){
				return roles.get(0);
			}
			return null;
	}

	public List<AclRole> getRoles() {
		return findAll();
	}

	@Override
	public List<AclRole> getRoleParentsByRoleId(Integer id) {
		Query query = getSession().createSQLQuery(
				"select * from getRoleParentsByRoleId(:id)")
				.setResultTransformer(Transformers.aliasToBean(AclRole.class))
				.setParameter("id", id);
			 
			@SuppressWarnings("unchecked")
			List<AclRole> result = query.list();
		return result;
	}

	@Override
	public List<AclRole> getRoleChildrenByRoleId(Integer id) {
		Query query = getSession().createSQLQuery(
				"select * from getRoleChildrenByRoleId(:id)")
				.setResultTransformer(Transformers.aliasToBean(AclRole.class))
				.setParameter("id", id);
			 
			@SuppressWarnings("unchecked")
			List<AclRole> result = query.list();
		return result;
	}
	
	@Override
	public boolean isChildRoleByRoleId(Integer childRoleId, Integer roleId)
	{
		Query query = getSession().createSQLQuery(
				"select * from isChildRoleByRoleId(:childRoleId, :roleId)")
				.setParameter("childRoleId", childRoleId)
				.setParameter("roleId", roleId);
		
		return (Boolean)query.list().get(0);
	}
}

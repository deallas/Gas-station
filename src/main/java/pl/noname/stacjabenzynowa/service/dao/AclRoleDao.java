package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;
import pl.noname.stacjabenzynowa.persistance.AclRole;

public interface AclRoleDao extends GenericDao<AclRole,Integer> {
	
	public AclRole getRoleById(Integer id);
	public AclRole getRoleByName(String name);
	public List<AclRole> getRoles();
	public List<AclRole> getRoleParentsByRoleId(Integer id);
	public List<AclRole> getRoleChildrenByRoleId(Integer id);
	public boolean isChildRoleByRoleId(Integer childRoleId, Integer roleId);
}
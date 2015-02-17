package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.AclRole;

public interface AclService 
{
	public AclResgroup getResgroupByName(String name);
	public AclRole getRoleById(Integer id);
	public AclRole getRoleByName(String name);
	public List<AclRole> getRoles();
	public List<AclRole> getRoleParentsByRoleId(Integer id);
	public List<AclRole> getRoleChildrenByRoleId(Integer id);
	public boolean isChildRoleByRoleId(Integer childRoleId, Integer roleId);
	public String encodePassword(String password);
	public boolean checkOldPassword(String oldPassword, String password);
}

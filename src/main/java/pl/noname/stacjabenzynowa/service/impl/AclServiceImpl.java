package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.dao.AclResgroupDao;
import pl.noname.stacjabenzynowa.service.dao.AclRoleDao;

@Service("aclService")
public class AclServiceImpl implements AclService
{
	private Logger logger = LoggerFactory.getLogger(AclServiceImpl.class);
	
	@Autowired
	private AclResgroupDao resgroupDao;
	
	@Autowired
	private AclRoleDao aclRoleDao;

	@Value("${auth.bcryptStrength}")
	private Integer bcryptStrength;
	
	private PasswordEncoder passwordEncoder = null;
	
	/* ----------------------------------------------------------- */
	
	public AclResgroupDao getResgroupDao() {
		return resgroupDao;
	}

	public void setResgroupDao(AclResgroupDao resgroupDao) {
		this.resgroupDao = resgroupDao;
	}
	
	public AclRoleDao getAclRoleDao() {
		return aclRoleDao;
	}

	public void setAclRoleDao(AclRoleDao aclRoleDao) {
		this.aclRoleDao = aclRoleDao;
	}
	
	/* ----------------------------------------------------------- */
	

	@Override
	@Transactional
	public AclRole getRoleById(Integer id){
		return aclRoleDao.getRoleById(id);
	}
	
	/* ----------------------------------------------------------- */
	@Override
	@Transactional
	public AclRole getRoleByName(String name) {
		return aclRoleDao.getRoleByName(name);
	}
	
	@Override
	@Transactional
	public List<AclRole> getRoles() {
		return aclRoleDao.getRoles();
	}
	
	/* ----------------------------------------------------------- */
	
	@Override
	@Transactional
	public AclResgroup getResgroupByName(String name) {
		return resgroupDao.getResgroupByName(name);
	}

	@Override
	@Transactional
	public List<AclRole> getRoleParentsByRoleId(Integer id) {
		return aclRoleDao.getRoleParentsByRoleId(id);
	}

	@Override
	@Transactional
	public List<AclRole> getRoleChildrenByRoleId(Integer id) {
		return aclRoleDao.getRoleChildrenByRoleId(id);
	}
	
	@Override
	public boolean isChildRoleByRoleId(Integer childRoleId, Integer roleId) {
		return aclRoleDao.isChildRoleByRoleId(childRoleId, roleId);
	}

	/* ----------------------------------------------------------- */
	
	public PasswordEncoder getPasswordEncoder()
	{
		if(passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
		}
		
		return passwordEncoder;
	}
	
	@Override
	public String encodePassword(String password) 
	{
		logger.debug("Encode password");
		
		return getPasswordEncoder().encode(password);
	}

	@Override
	public boolean checkOldPassword(String oldPassword, String password) 
	{
		logger.debug("Check old password");
		
		return getPasswordEncoder().matches(password, oldPassword);
	}
}

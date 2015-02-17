package pl.noname.stacjabenzynowa.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.AclRole;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class AclServiceTest
{
	
	private Logger logger = LoggerFactory.getLogger(AclServiceTest.class);
	
	@Resource(name="aclService")
	private AclService aclService;
	
	/* ----------------------------------------------------------- */
	
	public AclService getAclService() {
		return aclService;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}
	
	/* ----------------------------------------------------------- */
	
	@Test
	public void testGetResgroupByName()
	{
		AclResgroup resgroup = aclService.getResgroupByName("employee_panel");
		
		assertNotNull("Nie znaleziono grupy zasobow na podstawie nazwy", resgroup);

		resgroup = aclService.getResgroupByName("--nie istnieje--");
		
		assertNull("Dla nieistniejacej grupy funkcja powinna zwrocic null", resgroup);
	}
	
	@Test
	public void testGetRoles() {
		List<AclRole> roles = aclService.getRoles();
		AclRole r1 = new AclRole();
		r1.setId(1);
		r1.setName("GUE");
		AclRole r2 = new AclRole();
		r2.setId(2);
		r2.setName("USR");
		AclRole r3 = new AclRole();
		r3.setId(3);
		r3.setName("EMP");
		AclRole r4 = new AclRole();
		r4.setId(4);
		r4.setName("SEMP");
		AclRole r5 = new AclRole();
		r5.setId(5);
		r5.setName("OWN");
		AclRole r6 = new AclRole();
		r6.setId(6);
		r6.setName("GOD");

		List<AclRole> temp = Arrays.asList(r1,r2,r3,r4,r5,r6);
		assertEquals(temp.size(), roles.size());
		for (int i = 0; i < temp.size(); i++)
		    assertEquals("mismatch at " + i, temp.get(i).getName(), roles.get(i).getName());
	}
	
	@Test
	public void testGetRoleByName()
	{	
		AclRole role = aclService.getRoleByName("EMP");
		assertTrue(role instanceof AclRole);
		assertEquals("Pobrano bledna role", "EMP", role.getName());
		
		assertNull("Zwrocona wartosc dla nieistniejacej roli powinna byc null'em", aclService.getRoleByName("nieistniejaca"));
	}
	@Test
	public void testGetRoleParentsByRoleId() 
	{
		List<AclRole> roles = aclService.getRoleParentsByRoleId(6);
		AclRole r1 = new AclRole();
		r1.setId(1);
		r1.setName("GUE");
		AclRole r2 = new AclRole();
		r2.setId(3);
		r2.setName("EMP");
		AclRole r3 = new AclRole();
		r3.setId(4);
		r3.setName("SEMP");
		AclRole r4 = new AclRole();
		r4.setId(5);
		r4.setName("OWN");
		AclRole r5 = new AclRole();
		r5.setId(6);
		r5.setName("GOD");
		
		List<AclRole> temp = Arrays.asList(r5,r4,r3,r2,r1);
		assertEquals(temp.size(), roles.size());
		for (int i = 0; i < temp.size(); i++) {
			logger.info("Wartosci: ID = " + roles.get(i).getId() + ", Name = " + roles.get(i).getName());
		    assertEquals("mismatch at " + i, temp.get(i).getName(), roles.get(i).getName());
		}
	}
	
	@Test
	public void testGetRoleChildrenByRoleId() 
	{
		List<AclRole> roles = aclService.getRoleChildrenByRoleId(5);
		AclRole r1 = new AclRole();
		r1.setId(3);
		r1.setName("EMP");
		AclRole r2 = new AclRole();
		r2.setId(4);
		r2.setName("SEMP");
		List<AclRole> temp = Arrays.asList(r1,r2);
		assertEquals(temp.size(), roles.size());
		for (int i = 0; i < temp.size(); i++) {
			logger.info("Wartosci: ID = " + roles.get(i).getId() + ", Name = " + roles.get(i).getName());
		    assertEquals("mismatch at " + i, temp.get(i).getName(), roles.get(i).getName());
		}
	}
}

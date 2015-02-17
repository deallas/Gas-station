package pl.noname.stacjabenzynowa.service;

import java.util.Date;
import java.util.Calendar;

import javax.annotation.Resource;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class BlockerServiceTest
{
	@Resource(name="blockerService")
	private BlockerService blockerService;

	@Resource(name="aclService")
	private AclService aclService;
	
	@Resource(name="dateTimeService")
	private DateTimeService dateTimeService;
	
	private AclResgroup resgroup;
	
	/* ----------------------------------------------------------- */
	
	public BlockerService getBlockerService() {
		return blockerService;
	}

	public void setBlockerService(BlockerService blockerService) {
		this.blockerService = blockerService;
	}
	
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}	
	
	public AclService getAclService() {
		return aclService;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}
	
	/* ----------------------------------------------------------- */

	@Before
	public void setup()
	{
		dateTimeService = EasyMock.createMock(DateTimeService.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,12);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date date = cal.getTime();
		expect(dateTimeService.getCurrentDate()).andReturn(date).anyTimes();
		replay(dateTimeService);

		resgroup = aclService.getResgroupByName("employee_panel");
		blockerService.setDateTimeService(dateTimeService);
	}
	
	@Test
	public void testCountAttempts()
	{
		int c = blockerService.getCountAttempts();
		
		assertEquals(3, c);
	}
	
	@Test
	@Transactional
	public void testAddAttempt()
	{		
		int c = blockerService.getCountAttempts();
		BlockerAttempt attempt = blockerService.addAttempt("127.0.0.1", resgroup);
		assertTrue(attempt instanceof BlockerAttempt);
		
		int c2 = blockerService.getCountAttempts();
		assertEquals("Proba logowania nie zapisala sie do bazy", c+1, c2);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY,12);
		cal.set(Calendar.MINUTE,5);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		assertEquals(attempt.getDateExpired(), cal.getTime()); 
	}
	
	@Test
	@Transactional
	public void testIncrementAttempt()
	{
		BlockerAttempt attempt = blockerService.getActiveAttempt("192.168.123.100", resgroup);
		
		int attempts = attempt.getAttempts();
		blockerService.incrementAttempt(attempt);
		int attempts2 = attempt.getAttempts();
		
		assertEquals("Inkrementacja prob logowania niepowiodla sie", attempts+1, attempts2);
	}
	
	@Test
	@Transactional
	public void testSetAttemptExpired()
	{
		BlockerAttempt attempt = blockerService.getActiveAttempt("192.168.123.100", resgroup);
		blockerService.setAttemptExpired(attempt);
		
		assertEquals("Status przeterminowanych prob powinien zostac ustawiony na EXPIRED", attempt.getStatus(), BlockerAttempt.Status.EXPIRED);
	}
	
	@Test
	@Transactional
	public void testGetActiveAttempt()
	{
		BlockerAttempt attempt = blockerService.getActiveAttempt("192.168.123.100", resgroup);
		assertTrue(attempt instanceof BlockerAttempt);
		
		attempt = blockerService.getActiveAttempt("127.0.0.1", resgroup);
		assertNull("Przy nieistniejacym rekordzie w bazie powinien zwracac wartosc NULL", attempt);
	
		attempt = blockerService.getActiveAttempt("192.168.123.101", resgroup);
		assertNull("Pobrano przeterminowany rekord", attempt);
	}
}

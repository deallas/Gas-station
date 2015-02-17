package pl.noname.stacjabenzynowa.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.noname.stacjabenzynowa.persistance.RefuelingType;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class RefuelingServiceTest {

	private Logger logger = LoggerFactory.getLogger(RefuelingServiceTest.class);
	
	@Resource(name="refuelingService")
	private RefuelingService refuelingService;
	
	/* ----------------------------------------------------------- */
	
	public RefuelingService getRefuelingService() {
		return refuelingService;
	}

	public void setRefuelingService(RefuelingService refuelingService) {
		this.refuelingService = refuelingService;
	}

	/* ----------------------------------------------------------- */

	@Test
	public void testGetRefuelingTypes() {
		List<RefuelingType> types = refuelingService.getRefuelingTypes();
		RefuelingType r1 = new RefuelingType("E95",new BigDecimal("5.64"),1);
		r1.setId(1);
		RefuelingType r2 = new RefuelingType("E98",new BigDecimal("5.84"),1);
		r2.setId(2);
		RefuelingType r3 = new RefuelingType("ON",new BigDecimal("5.56"),1);
		r3.setId(3);
		RefuelingType r4 = new RefuelingType("LPG",new BigDecimal("2.54"),1);
		r4.setId(4);

		List<RefuelingType> temp = Arrays.asList(r1,r2,r3,r4);
		assertEquals(temp.size(), types.size());
		for (int i = 0; i < temp.size(); i++) {
			logger.info("Wartosci: ID = " + types.get(i).getId() + ", Name = " + types.get(i).getName());
		    assertEquals("mismatch at " + i, temp.get(i).getName(), types.get(i).getName());
		}
	}	
}

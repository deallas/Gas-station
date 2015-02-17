package pl.noname.stacjabenzynowa.service;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.service.dao.CarWashTypeDao;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class CarWashServiceTest
{
	private Logger logger = LoggerFactory.getLogger(RefuelingServiceTest.class);

	@Resource(name="carWashService")
	private CarWashService carWashService;
	
	/* ----------------------------------------------------------- */
	
	public CarWashService getCarWashService() {
		return carWashService;
	}

	public void setCarWashService(CarWashService carWashService) {
		this.carWashService = carWashService;
	}
	
	/* ----------------------------------------------------------- */

	@Test
	@Transactional
	public void testCreateCarWashType()
	{	
		CarWashTypeDao carWashTypeDao = carWashService.getCarWashTypeDao();
		Integer i = carWashTypeDao.findCount();
		CarWashType carWashType = new CarWashType("testowy", new BigDecimal(100.0d), 50);
		
		carWashService.createCarWashType(carWashType);
		
		i++;
		assertEquals("Nie dodano typu mycia w myjni", i, carWashTypeDao.findCount());
	}	

	/* ----------------------------------------------------------- */

	@Test
	public void testGetCarWashTypes() 
	{
		List<CarWashType> types = carWashService.getCarWashTypes();
		CarWashType r1 = new CarWashType("Automatyczna",new BigDecimal("29.99"),1);
		r1.setId(1);
		CarWashType r2 = new CarWashType("Ręczna samoobsługowa",new BigDecimal("19.99"),1);
		r2.setId(2);
		CarWashType r3 = new CarWashType("Ręczna niesamoobsługowa",new BigDecimal("25.00"),1);
		r3.setId(3);

		List<CarWashType> temp = Arrays.asList(r1,r2,r3);
		assertEquals(temp.size(), types.size());
		for (int i = 0; i < temp.size(); i++) {
			logger.info("Wartosci: ID = " + types.get(i).getId() + ", Name = " + types.get(i).getName());
		    assertEquals("mismatch at " + i, temp.get(i).getName(), types.get(i).getName());
		}
	}
}

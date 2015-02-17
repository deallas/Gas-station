package pl.noname.stacjabenzynowa.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.PetrolService;

@Service
public class AddPetrolContainerPressureTask 
{
	private static final double PRESSURE_MEAN = 1000d;
	private static final double PRESSURE_VARIANCE = 200d;
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@Scheduled(cron="0 0/5 * * * ?")
	public void addPetrolContainerPressure() 
	{	
		Date currentDate = dateTimeService.getCurrentDate();
		
		List<PetrolContainer> containers = petrolService.getPetrolContainers();
		
		for(PetrolContainer container : containers) {
			
			PetrolContainerMeasurement measurement = new PetrolContainerMeasurement();
			
			measurement.setMeasurementDate(currentDate);
			measurement.setPetrolContainer(container);
			measurement.setType(PetrolContainerMeasurement.Type.PRESSURE);
			
			Random fRandom = new Random();
			double value = PRESSURE_MEAN + fRandom.nextGaussian() * PRESSURE_VARIANCE;
			measurement.setValue(new BigDecimal(value));
			
			petrolService.createPetrolContainerMeasurement(measurement);
		}
	}
	
}

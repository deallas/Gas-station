package pl.noname.stacjabenzynowa.task;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.email.EmailServiceInterface;
import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.util.DateTimestampRemover;

//@Service
public class PetrolLevelTask {
	
	private static final Logger logger = LoggerFactory.getLogger(PetrolLevelTask.class);
	
	@Autowired
	private EmailServiceInterface emailService;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private AclService aclService;
	
	@Autowired
	private EmployeeService employeeService;
	
	/* ----------------------------------------------------------- */
	
	public EmailServiceInterface getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceInterface emailService) {
		this.emailService = emailService;
	}
	
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	public PetrolService getPetrolService() {
		return petrolService;
	}

	public void setPetrolService(PetrolService petrolService) {
		this.petrolService = petrolService;
	}
	
	public AclService getAclService() {
		return aclService;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/* ----------------------------------------------------------- */

	//@Scheduled(cron="0 0/1 * * * ?")
	@Transactional
	public void checkPetrolLevel() {
		logger.info("checkPetrolLevel");
		Date date = DateTimestampRemover.removeTimestamp(dateTimeService.getCurrentDate());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);	
		calendar.add(Calendar.DATE, -1);
		
		Date dateMinusOneDay = calendar.getTime();
		
		List<PetrolContainerMeasurement> measurements = petrolService.getPetrolContainerMeasurements();
		List<Employee> semp = employeeService.getEmployeesByRole(aclService.getRoleByName("SEMP"));
		String temp = "";
		if(measurements != null && !(measurements.isEmpty())) {
			for(PetrolContainerMeasurement measurement : measurements) {
			
				Date measurementDate = DateTimestampRemover.removeTimestamp(measurement.getMeasurementDate());
			
				if(measurementDate.after(dateMinusOneDay)){
					if(measurement.getValue().compareTo(new BigDecimal(10.00)) == -1) {
					
						temp = temp + "Id: " + measurement.getPetrolContainer().getId() + "\nTyp: "
								+ measurement.getPetrolContainer().getType().toString() + "\nPoziom paliwa: " + measurement.getValue().toString() + "\n\n";
					}
				}
			}
		
			String subject = "Krytyczny poziom paliwa";
			String text = "Krytyczny poziom paliwa w zbiornikach: \n\n" + temp;
		
			if (semp != null && !(semp.isEmpty())) {
				for(Employee emp : semp) {
					try {
						if (emp.getStatus() == Employee.Status.ACTIVE) {
							emailService.sendEmail(emp.getEmail(), subject, text);
						}
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

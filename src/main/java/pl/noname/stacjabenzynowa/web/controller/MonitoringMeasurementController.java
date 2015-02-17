package pl.noname.stacjabenzynowa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.web.form.PetrolContainerMeasurementEditForm;

@RequestMapping(value = "/admin/monitoring/measurement")
@Controller
public class MonitoringMeasurementController {
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("petrolContainerMeasurementPaginator", petrolService.getPaginatorPetrolContainerMeasurements(pageNumber,order,ascing));
		model.addAttribute("page","measurement");
		
		return "monitoring:list:measurement";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editContainerMeasurement(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backmeasurement");
		
		if (!(request.isUserInRole("ROLE_SEMP") || request.isUserInRole("ROLE_GOD"))) {
	    	redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.access_denied", null, request.getLocale()));	
			return "redirect:/admin/monitoring/measurement/";
	    } else {
		
	    PetrolContainerMeasurement measurement = petrolService.getPetrolContainerMeasurementById(id);
	    
	    if(measurement == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/monitoring/measurement/";
		}
	    	    
		PetrolContainerMeasurementEditForm measurementForm = new PetrolContainerMeasurementEditForm();
		measurementForm.populateByPetrolContainerMeasurement(measurement);
		
		model.addAttribute("measurementDate", measurement.getMeasurementDate());
		model.addAttribute("containers", petrolService.getPetrolContainers());
		model.addAttribute("petrolContainerMeasurementEditForm", measurementForm);
		
		return "monitoring:edit:measurement";
	    }
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editContainerMeasurement(@Valid PetrolContainerMeasurementEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backmeasurement");
		
		if (!(request.isUserInRole("ROLE_SEMP") || request.isUserInRole("ROLE_GOD"))) {
	    	redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.access_denied", null, request.getLocale()));	
			return "redirect:/admin/monitoring/measurement/";
	    } else {
		
		PetrolContainerMeasurement measurement = petrolService.getPetrolContainerMeasurementById(id);
		 
		if(measurement == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/monitoring/measurement/";
		}
		if(result.hasErrors())
		{
			model.addAttribute("measurementDate", measurement.getMeasurementDate());
			model.addAttribute("containers", petrolService.getPetrolContainers());
			return "monitoring:edit:measurement";
		}
		
		Integer containerId = new Integer(String.valueOf(form.getContainerId()));
		form.setContainer(petrolService.getPetrolContainerById(containerId));
		
		petrolService.updatePetrolContainerMeasurement(form.toPetrolContainerMeasurement());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/monitoring/measurement/";
        
	    }
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteContainerMeasurement(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		if (!(request.isUserInRole("ROLE_SEMP") || request.isUserInRole("ROLE_GOD"))) {
	    	redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.access_denied", null, request.getLocale()));	
			return "redirect:/admin/monitoring/measurement/";
	    } else {
		
		PetrolContainerMeasurement mea = petrolService.getPetrolContainerMeasurementById(id);
		
		if(mea == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/monitoring/measurement/";
		}
	    
		petrolService.deletePetrolContainerMeasurement(mea);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/monitoring/measurement/";
	    
	    }
	}
}

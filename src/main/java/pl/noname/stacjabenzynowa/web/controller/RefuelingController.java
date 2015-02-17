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

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.service.RefuelingService;
import pl.noname.stacjabenzynowa.web.form.RefuelingAddForm;
import pl.noname.stacjabenzynowa.web.form.RefuelingEditForm;

@RequestMapping(value = "/admin/petrol/refueling")
@Controller
public class RefuelingController {
	
	@Autowired
	private RefuelingService refuelingService;
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("petrolRefuelingPaginator", refuelingService.getPaginatorRefuelings(pageNumber,order,ascing));
		model.addAttribute("page","refueling");
		
		return "petrol:list:refueling";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addRefueling(Model model,
					HttpServletRequest request,
					RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefueling");
	    	    
		model.addAttribute("containers", petrolService.getPetrolContainers());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("refuelingTypes", refuelingService.getRefuelingTypes());	
		model.addAttribute("refuelingAddForm", new RefuelingAddForm());
		
		return "petrol:add:refueling";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addRefueling(@Valid RefuelingAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefueling");
		
		if(result.hasErrors())
		{
			model.addAttribute("containers", petrolService.getPetrolContainers());
			model.addAttribute("clients", clientService.getClients());
			model.addAttribute("refuelingTypes", refuelingService.getRefuelingTypes());	
			return "petrol:add:refueling";
		}
		
		Integer containerId = new Integer(String.valueOf(form.getContainerId()));
		form.setPetrolContainer(petrolService.getPetrolContainerById(containerId));
		
		Integer clientId = new Integer(String.valueOf(form.getClientId()));
		form.setClient(clientService.getClientById(clientId));
		
		Integer refuelingTypeId = new Integer(String.valueOf(form.getRefuelingTypeId()));
		form.setRefuelingType(refuelingService.getRefuelingTypeById(refuelingTypeId));

		Refueling refueling = form.toRefueling();
		
		refuelingService.createRefueling(refueling);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/refueling/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editRefueling(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefueling");
		
		Refueling refueling = refuelingService.getRefuelingById(id);
	    
	    if(refueling == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refueling/";
		}
		
	    model.addAttribute("refuelingDate", refueling.getRefuelingDate());
	    model.addAttribute("containers", petrolService.getPetrolContainers());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("refuelingTypes", refuelingService.getRefuelingTypes());
	    
	    RefuelingEditForm refuelingForm = new RefuelingEditForm();
	    refuelingForm.populateByRefueling(refueling);
		
		model.addAttribute("refuelingEditForm", refuelingForm);
		
		return "petrol:edit:refueling";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editRefueling(@Valid RefuelingEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backrefueling");
	    
		Refueling refueling = refuelingService.getRefuelingById(id);
		
		if(refueling == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refueling/";
		}
		if(result.hasErrors())
		{
			model.addAttribute("refuelingDate", refueling.getRefuelingDate());
			model.addAttribute("containers", petrolService.getPetrolContainers());
			model.addAttribute("clients", clientService.getClients());
			model.addAttribute("refuelingTypes", refuelingService.getRefuelingTypes());
			return "petrol:edit:refueling";
		}
		
		Client client = clientService.getClientById(Integer.valueOf(Long.valueOf(form.getClientId()).intValue()));
		form.setClient(client);
		PetrolContainer container = petrolService.getPetrolContainerById(Integer.valueOf(Long.valueOf(form.getContainerId()).intValue()));
		form.setContainer(container);
		RefuelingType refuelingType = refuelingService.getRefuelingTypeById(Integer.valueOf(Long.valueOf(form.getRefuelingTypeId()).intValue()));
		form.setRefuelingType(refuelingType);
		
		refuelingService.updateRefueling(form.toRefueling());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/refueling/";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteRefueling(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Refueling ref = refuelingService.getRefuelingById(id);
		
		if(ref == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refueling/";
		}
	    
		refuelingService.deleteRefueling(ref);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/petrol/refueling/";
	
	}
}

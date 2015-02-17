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

import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.web.form.PetrolDeliveryAddForm;
import pl.noname.stacjabenzynowa.web.form.PetrolDeliveryEditForm;

@RequestMapping(value = "/admin/petrol/delivery")
@Controller
public class PetrolDeliveryController {
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("petrolDeliveryPaginator", petrolService.getPaginatorPetrolDeliveries(pageNumber,order,ascing));
		model.addAttribute("page","delivery");
		
		return "petrol:list:delivery";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addDelivery(Model model,
					HttpServletRequest request,
					RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backdelivery");
	    
		model.addAttribute("providers", petrolService.getPetrolProviders());
		model.addAttribute("petrolDeliveryAddForm", new PetrolDeliveryAddForm());
		
		return "petrol:add:delivery";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addDelivery(@Valid PetrolDeliveryAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backdelivery");
		
		if(result.hasErrors())
		{
			model.addAttribute("providers", petrolService.getPetrolProviders());
			return "petrol:add:delivery";
		}
		
		Integer providerId = new Integer(String.valueOf(form.getProviderId()));
		form.setProvider(petrolService.getPetrolProviderById(providerId));
		PetrolDelivery delivery = form.toPetrolDelivery();

		petrolService.createPetrolDelivery(delivery);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/delivery/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editDelivery(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backdelivery");
		
		PetrolDelivery delivery = petrolService.getPetrolDeliveryById(id);
	    
	    if(delivery == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/delivery/";
		}
	    	    
	    PetrolDeliveryEditForm deliveryForm = new PetrolDeliveryEditForm();
	    deliveryForm.populateByPetrolDelivery(delivery);
		
	    model.addAttribute("deliveryDate", delivery.getDeliveryDate());
	    model.addAttribute("orderDate", delivery.getOrderDate());
	    model.addAttribute("providers", petrolService.getPetrolProviders());
		model.addAttribute("petrolDeliveryEditForm", deliveryForm);
		
		return "petrol:edit:delivery";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editDelivery(@Valid PetrolDeliveryEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backdelivery");
	    
		PetrolDelivery delivery = petrolService.getPetrolDeliveryById(id);
		
		if(delivery == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/delivery/";
		}
		if(result.hasErrors())
		{
			model.addAttribute("deliveryDate", delivery.getDeliveryDate());
			model.addAttribute("orderDate", delivery.getOrderDate());
			model.addAttribute("providers", petrolService.getPetrolProviders());
			return "petrol:edit:delivery";
		}
		
		Integer providerId = new Integer(String.valueOf(form.getProviderId()));
		form.setProvider(petrolService.getPetrolProviderById(providerId));
		
		petrolService.updatePetrolDelivery(form.toPetrolDelivery());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/delivery/";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteDelivery(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		PetrolDelivery del = petrolService.getPetrolDeliveryById(id);
		
		if(del == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/delivery/";
		}
	    
		petrolService.deletePetrolDelivery(del);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/petrol/delivery/";
	
	}
}

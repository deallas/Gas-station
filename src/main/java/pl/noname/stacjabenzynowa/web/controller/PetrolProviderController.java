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

import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.web.form.PetrolProviderAddForm;
import pl.noname.stacjabenzynowa.web.form.PetrolProviderEditForm;

@RequestMapping(value = "/admin/petrol/provider")
@Controller
public class PetrolProviderController {

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
		
		model.addAttribute("petrolProviderPaginator", petrolService.getPaginatorPetrolProviders(pageNumber,order,ascing));
		model.addAttribute("page","provider");
		
		return "petrol:list:provider";
	}

	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addProvider(Model model,
					HttpServletRequest request,
					RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backprovider");
	    
		model.addAttribute("petrolProviderAddForm", new PetrolProviderAddForm());
		
		return "petrol:add:provider";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addProvider(@Valid PetrolProviderAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backprovider");
		
		if(result.hasErrors())
		{
			return "petrol:add:provider";
		}
		
		petrolService.createPetrolProvider(form.toPetrolProvider());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/provider/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editProvider(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backprovider");
		
	    PetrolProvider prov = petrolService.getPetrolProviderById(id);
	    
	    if(prov == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/provider/";
		}
	    	    
		PetrolProviderEditForm provForm = new PetrolProviderEditForm();
		provForm.populateByPetrolProvider(prov);
		
		model.addAttribute("petrolProviderEditForm", provForm);
		
		return "petrol:edit:provider";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editProvider(@Valid PetrolProviderEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backprovider");
	    
		PetrolProvider prov = petrolService.getPetrolProviderById(id);
		if(prov == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/provider/";
		}
		if(result.hasErrors())
		{
			return "petrol:edit:provider";
		}
		
		petrolService.updatePetrolProvider(form.toPetrolProvider());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/provider/";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteProvider(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		PetrolProvider prov = petrolService.getPetrolProviderById(id);
		
		if(prov == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/provider/";
		}
	    
		petrolService.deletePetrolProvider(prov);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/petrol/provider/";
	
	}
	
}

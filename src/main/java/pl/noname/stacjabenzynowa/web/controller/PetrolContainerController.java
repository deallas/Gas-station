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

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.web.form.PetrolContainerAddForm;
import pl.noname.stacjabenzynowa.web.form.PetrolContainerEditForm;

@RequestMapping(value = "/admin/petrol/container")
@Controller
public class PetrolContainerController {

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
		
		model.addAttribute("petrolContainerPaginator", petrolService.getPaginatorPetrolContainers(pageNumber,order,ascing));
		model.addAttribute("page","container");
		
		return "petrol:list:container";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addContainer(Model model,
					HttpServletRequest request,
					RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcontainer");
	    	    
		model.addAttribute("petrolContainerAddForm", new PetrolContainerAddForm());
		
		return "petrol:add:container";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addContainer(@Valid PetrolContainerAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcontainer");
		
		if(result.hasErrors())
		{
			return "petrol:add:container";
		}
		
		petrolService.createPetrolContainer(form.toPetrolContainer());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/container/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editContainer(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcontainer");
		
		PetrolContainer container = petrolService.getPetrolContainerById(id);
	    
	    if(container == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/container/";
		}
	    	    
		PetrolContainerEditForm containerForm = new PetrolContainerEditForm();
		containerForm.populateByPetrolContainer(container);
		
		model.addAttribute("petrolContainerEditForm", containerForm);
		
		return "petrol:edit:container";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editContainer(@Valid PetrolContainerEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backcontainer");
	    
		PetrolContainer container = petrolService.getPetrolContainerById(id);
		if(container == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/container/";
		}
		if(result.hasErrors())
		{
			return "petrol:edit:container";
		}
		
		petrolService.updatePetrolContainer(form.toPetrolContainer());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/container/";
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteContainer(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		PetrolContainer con = petrolService.getPetrolContainerById(id);
		
		if(con == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/container/";
		}
	    
		petrolService.deletePetrolContainer(con);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/petrol/container/";
	
	}
	
}

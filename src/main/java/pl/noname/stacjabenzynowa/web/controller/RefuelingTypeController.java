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

import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.service.RefuelingService;
import pl.noname.stacjabenzynowa.web.form.RefuelingTypeAddForm;
import pl.noname.stacjabenzynowa.web.form.RefuelingTypeEditForm;

@RequestMapping(value = "/admin/petrol/refuelingtype")
@Controller
public class RefuelingTypeController {
	
	@Autowired
	private RefuelingService refuelingService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("petrolRefuelingTypePaginator", refuelingService.getPaginatorRefuelingTypes(pageNumber,order,ascing));
		model.addAttribute("page","refuelingtype");
		
		return "petrol:list:refuelingtype";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addRefuelingType(Model model,
					HttpServletRequest request,
					RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefuelingtype");
	    	    
		model.addAttribute("refuelingTypeAddForm", new RefuelingTypeAddForm());
		
		return "petrol:add:refuelingtype";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addRefuelingType(@Valid RefuelingTypeAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefuelingtype");
		
		if(result.hasErrors())
		{
			return "petrol:add:refuelingtype";
		}
		
		refuelingService.createRefuelingType(form.toRefuelingType());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/refuelingtype/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editRefuelingType(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backrefuelingtype");
		
		RefuelingType refuelingType = refuelingService.getRefuelingTypeById(id);
	    
	    if(refuelingType == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refuelingtype/";
		}
	    	    
	    RefuelingTypeEditForm refuelingTypeForm = new RefuelingTypeEditForm();
	    refuelingTypeForm.populateByRefuelingType(refuelingType);
		
		model.addAttribute("refuelingTypeEditForm", refuelingTypeForm);
		
		return "petrol:edit:refuelingtype";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editRefuelingType(@Valid RefuelingTypeEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backrefuelingtype");
	    
		RefuelingType refuelingType = refuelingService.getRefuelingTypeById(id);
		
		if(refuelingType == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refuelingtype/";
		}
		if(result.hasErrors())
		{
			return "petrol:edit:refuelingtype";
		}
		
		refuelingService.updateRefuelingType(form.toRefuelingType());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/petrol/refuelingtype/";
	}
	

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteRefuelingType(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		RefuelingType type = refuelingService.getRefuelingTypeById(id);
		
		if(type == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/petrol/refuelingtype/";
		}
		
	    try {
		refuelingService.deleteRefuelingType(type);
	    }
	    catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage(e.getMessage(), null, request.getLocale()));
			return "redirect:/admin/petrol/refuelingtype/";
	    }
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/petrol/refuelingtype/";
	
	}
}

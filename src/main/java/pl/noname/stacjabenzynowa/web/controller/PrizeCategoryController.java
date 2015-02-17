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

import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.service.PrizeService;
import pl.noname.stacjabenzynowa.web.form.PrizeCategoryAddForm;
import pl.noname.stacjabenzynowa.web.form.PrizeCategoryEditForm;

@RequestMapping(value = "/admin/loyalty/category")
@Controller
public class PrizeCategoryController {
	
	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("prizeCategoryPaginator", prizeService.getPaginatorPrizeCategories(pageNumber,order,ascing));
		model.addAttribute("page","category");
		
		return "loyalty:list:category";
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
		model.addAttribute("page","backcategory");
	    	    
		model.addAttribute("prizeCategoryAddForm", new PrizeCategoryAddForm());
		
		return "loyalty:add:category";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addContainer(@Valid PrizeCategoryAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcategory");
		
		if(result.hasErrors())
		{
			return "loyalty:add:category";
		}
		
		prizeService.createPrizeCategory(form.toPrizeCategory());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/loyalty/category/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editContainer(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcategory");
		
		PrizeCategory  category = prizeService.getPrizeCategoryById(id);
	    
	    if(category == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/loyalty/category/";
		}
	    	    
		PrizeCategoryEditForm categoryForm = new PrizeCategoryEditForm();
		categoryForm.populateByPrizeCategory(category);
		
		model.addAttribute("prizeCategoryEditForm", categoryForm);
		
		return "loyalty:edit:category";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String editContainer(@Valid PrizeCategoryEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backcategory");
	    
		PrizeCategory  category = prizeService.getPrizeCategoryById(id);
		
		if(category == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/loyalty/category/";
		}
		if(result.hasErrors())
		{
			return "loyalty:edit:category";
		}
		
		prizeService.updatePrizeCategory(form.toPrizeCategory());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/loyalty/category/";
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteContainer(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		PrizeCategory  category = prizeService.getPrizeCategoryById(id);
		
		if(category == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/admin/loyalty/category/";
		}
	    
		try {
		prizeService.deletePrizeCategory(category);
		}
		catch (Exception e) {
		redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
				messageSource.getMessage(e.getMessage(), null, request.getLocale()));
		return "redirect:/admin/loyalty/category/";
		}
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
		return "redirect:/admin/loyalty/category/";
	
	}
}

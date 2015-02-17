package pl.noname.stacjabenzynowa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.service.CarWashService;
import pl.noname.stacjabenzynowa.web.form.CarWashTypeAddForm;
import pl.noname.stacjabenzynowa.web.form.CarWashTypeEditForm;
import pl.noname.stacjabenzynowa.web.paginator.CarWashTypeOptions;

@RequestMapping(value = "/admin/carwashtype")
@Controller
public class CarWashTypesController {
	
	private Logger logger = LoggerFactory.getLogger(CarWashTypesController.class);

	@Autowired
	private CarWashService carWashService; 
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		model.addAttribute("paginator", carWashService.getCarWashTypePaginator(pageNumber,order,ascing));
		model.addAttribute("page","carwashtypes");
		
		return "carwashtype:list";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String empList(Model model,
			   			   @RequestParam("opt-orders[]") String[] orders,
			   			   @RequestParam("opt-order") String order,
			   			   @RequestParam("opt-items") Integer items,
			   			   @RequestParam("opt-ascing") Boolean ascing,
			   			   @RequestParam(value="reset", required=false) String reset)
	{
		CarWashTypeOptions paginatorOptions = carWashService.getCarWashTypePaginatorOptions();
        if(reset != null) {
        	paginatorOptions.clearPaginatorOptions();
        } else {
        	paginatorOptions.setPaginatorOptions(orders, order, items, ascing);              
        }
		
		return "redirect:/admin/carwashtype/";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String cwtAdd(HttpServletRequest request,
			Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwashtype");
	    	  		
		model.addAttribute("carWashTypeAddForm", new CarWashTypeAddForm());
		return "carwashtype:add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String cwtAdd(@Valid CarWashTypeAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwashtype");
		
		if(result.hasErrors())
		{
			return "carwashtype:add";
		}
		
		carWashService.createCarWashType(form.toCarWashType());
		
		logger.info("Add car wash type " + form.getName() + " success");
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.carwashtype.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/carwashtype/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editDelivery(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwashtype");
		
		CarWashType carWashType = carWashService.getCarWashTypeById(id);
	    
	    if(carWashType == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwashtype.not_found", null, request.getLocale()));
			return "redirect:/admin/carwashtype/";
		}
	    	    
	    CarWashTypeEditForm carWashTypeForm = new CarWashTypeEditForm();
	    carWashTypeForm.populateByCarWashType(carWashType);
		
		model.addAttribute("carWashTypeEditForm", carWashTypeForm);
		
		return "carwashtype:edit";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String cwtEdit(@Valid CarWashTypeEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backcarwashtype");
	    
		CarWashType carWashType = carWashService.getCarWashTypeById(id);
		
		if(carWashType == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwashtype.not_found", null, request.getLocale()));
			return "redirect:/admin/carwashtype/";
		}
		if(result.hasErrors())
		{
			return "carwashtype:edit";
		}
		
		carWashService.updateCarWashType(form.toCarWashType());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.carwashtype.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/carwashtype/";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String cwtDelete(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		CarWashType carWashType = carWashService.getCarWashTypeById(id);
		
		if(carWashType == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwashtype.not_found", null, request.getLocale()));
			return "redirect:/admin/carwashtype/";
		}
	    
		carWashService.deleteCarWashType(carWashType);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.carwashtype.delete_ok", null, request.getLocale()));
		
		return "redirect:/admin/carwashtype/";
	}
}
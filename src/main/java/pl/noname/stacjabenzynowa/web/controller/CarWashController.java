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

import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.service.CarWashService;
import pl.noname.stacjabenzynowa.web.form.CarWashAddForm;
import pl.noname.stacjabenzynowa.web.form.CarWashEditForm;


@RequestMapping(value = "/admin/carwash")
@Controller
public class CarWashController {
	
	private Logger logger = LoggerFactory.getLogger(CarWashController.class);

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
		model.addAttribute("paginator", carWashService.getCarWashPaginator(pageNumber, order, ascing));
		model.addAttribute("page","carwash");
		
		return "carwash:list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String cwtAdd(@Valid CarWashAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwash");
		
			
		if(result.hasErrors())
		{
			return "carwash:add";
		}
		
		Integer carWashTypeId = new Integer(String.valueOf(form.getCarWashTypeId()));
		form.setCarWashType(carWashService.getCarWashTypeById(carWashTypeId));
		carWashService.createCarWash(form.toCarWash());
		
		logger.info("Add car wash " + form.getId() + " success");
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.carwash.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/carwash/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String cwtAdd(HttpServletRequest request,
			Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwash");
		model.addAttribute("carWashTypes",carWashService.getCarWashTypes());	  		
		model.addAttribute("carWashAddForm", new CarWashAddForm());
		return "carwash:add";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editDelivery(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backcarwash");
		
		CarWash carWash = carWashService.getCarWashById(id);
	    
	    if(carWash == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwash.not_found", null, request.getLocale()));
			return "redirect:/admin/carwash/";
		}
	    	    
	    CarWashEditForm carWashForm = new CarWashEditForm();
	    carWashForm.populateByCarWash(carWash);
		
	    model.addAttribute("dateBeginWash", carWash.getDateBeginWash());
	    model.addAttribute("dateEndWash", carWash.getDateEndWash());
	    model.addAttribute("carWashTypes",carWashService.getCarWashTypes());
		model.addAttribute("carWashTypeEditForm", carWashForm);
		
		return "carwash:edit";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String cwtEdit(@Valid CarWashEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backpetrol");
	    
		CarWash carWash = carWashService.getCarWashById(id);
		
		if(carWash == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwash.not_found", null, request.getLocale()));
			return "redirect:/admin/carwash/";
		}
		if(result.hasErrors())
		{
			return "carwash:edit";
		}
		
		carWashService.updateCarWash(form.toCarWash());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.carwash.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/carwash/";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String cwtDelete(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		CarWash carWash = carWashService.getCarWashById(id);
		
		if(carWash == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.carwash.not_found", null, request.getLocale()));
			return "redirect:/admin/carwash/";
		}
	    
		carWashService.deleteCarWash(carWash);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.carwash.delete_ok", null, request.getLocale()));
		return "redirect:/admin/carwash/";
	}
	
}
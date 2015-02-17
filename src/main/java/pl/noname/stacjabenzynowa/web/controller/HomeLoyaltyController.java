package pl.noname.stacjabenzynowa.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.Prize;
import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.PrizeService;
import pl.noname.stacjabenzynowa.util.SBUtils;

@RequestMapping(value = "/loyalty")
@Controller
public class HomeLoyaltyController {

	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loyalty(Model model,HttpServletRequest request,
				RedirectAttributes redirectAttributes,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
			SBUtils.isClient(model,request);
			model.addAttribute("page", "loyalty");
			
			model.addAttribute("prizeCategoryPaginator", prizeService.getPaginatorPrizeCategories(pageNumber,order,ascing));
			
			try {
				User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			    String name = user.getUsername(); //get logged in username
			    
			    Client cli = clientService.getClientByEmail(name);
				model.addAttribute("isClient",cli);
				}
				catch (Exception e) {
					model.addAttribute("isClient", null);
					return "home:loyalty";
				}	
			
			return "home:loyalty";
	}
	
	@RequestMapping(value="/choose/{id}", method = RequestMethod.GET)
	public String choosePrize(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		SBUtils.isClient(model,request);
		
		if(!(request.isUserInRole("ROLE_USR"))) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.no_access_to_function", null, request.getLocale()));
			return "redirect:/loyalty/";
		}
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client loggedClient = clientService.getClientByEmail(name);
		
	    PrizeCategory category = prizeService.getPrizeCategoryById(id);
	    
		if(category == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/loyalty/";
		}
	    
		if(loggedClient.getPoints() >= category.getMinPoints()) {
			Prize prize = new Prize();
			Date date = dateTimeService.getCurrentDate();
			prize.setDateOfSelection(date);
			prize.setStatus(Prize.Status.WAITING);
			prize.setClient(loggedClient);
			prize.setCategory(category);
			
			prizeService.createPrize(prize);
			
			loggedClient.setPoints(loggedClient.getPoints()-category.getMinPoints());
			
			clientService.updateClient(loggedClient);
			
			redirectAttributes.addFlashAttribute("INFO_MESSAGE",
					messageSource.getMessage("controller.choose_ok", null, request.getLocale()));
			return "redirect:/loyalty/";
		} else {
		
		redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
				messageSource.getMessage("controller.choose_failed", null, request.getLocale()));
		return "redirect:/loyalty/";
		}
	}
}

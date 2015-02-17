package pl.noname.stacjabenzynowa.web.controller;

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
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.PrizeService;

@RequestMapping(value = "/client/account/prize")
@Controller
public class AccountPrizeController {

	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loyalty(Model model,HttpServletRequest request,
				RedirectAttributes redirectAttributes,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{	
			model.addAttribute("page","profile");
			
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String name = user.getUsername(); //get logged in username
		    
		    Client loggedClient = clientService.getClientByEmail(name);
			model.addAttribute("client",loggedClient);
			
			model.addAttribute("prizePaginator", prizeService.getPaginatorPrizesByClientId(pageNumber, order, ascing, loggedClient.getId()));
			
			return "account:list:prize";
	}
	
	@RequestMapping(value="/cancel/{id}", method = RequestMethod.GET)
	public String cancelPrize(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client loggedClient = clientService.getClientByEmail(name);
		
		Prize prize = prizeService.getPrizeById(id);
		
		if(prize == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.not_found", null, request.getLocale()));
			return "redirect:/client/account/prize/";
		}
	    
		if (prize.getStatus() == Prize.Status.WAITING) {
		
			prizeService.deletePrize(prize);
			
			loggedClient.setPoints(loggedClient.getPoints() + prize.getCategory().getMinPoints());
			clientService.updateClient(loggedClient);
			
			redirectAttributes.addFlashAttribute("INFO_MESSAGE",
					messageSource.getMessage("controller.delete_ok", null, request.getLocale()));
			return "redirect:/client/account/prize/";
		
		} else {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.delete_prize_failed", null, request.getLocale()));
			return "redirect:/client/account/prize/";
		}
	}
	
}

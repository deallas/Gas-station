package pl.noname.stacjabenzynowa.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.CarWashService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.PrizeService;
import pl.noname.stacjabenzynowa.service.RefuelingService;
import pl.noname.stacjabenzynowa.util.SBUtils;

@Controller
public class HomeController 
{	
	private static final Logger logger = LoggerFactory.getLogger(PanelController.class);
 
	@Autowired
	private AclService aclService;
	
	@Autowired
	private RefuelingService refuelingService;
	
	@Autowired
	private CarWashService carWashService;
	
	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) 
	{
		SBUtils.isClient(model,request);
		
		model.addAttribute("page", "home");
		logger.info("Witaj w domu! The client locale is {}.", locale);
		logger.info("IS CLIENT: {}" , request.isUserInRole("ROLE_USR"));
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		try {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client cli = clientService.getClientByEmail(name);
		model.addAttribute("isClient",cli);
		}
		catch (Exception e) {
			model.addAttribute("isClient", null);
			return "home:index";
		}
		return "home:index";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model,HttpServletRequest request) 
	{
		SBUtils.isClient(model,request);
		model.addAttribute("page", "contact");
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String name = user.getUsername(); //get logged in username
		    
		    Client cli = clientService.getClientByEmail(name);
			model.addAttribute("isClient",cli);
			}
			catch (Exception e) {
				model.addAttribute("isClient", null);
				return "home:contact";
			}	
		
		return "home:contact";
	}
	
	@RequestMapping(value = "/pricelist", method = RequestMethod.GET)
	public String pricelist(Model model,
				HttpServletRequest request,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{	
		SBUtils.isClient(model,request);
		model.addAttribute("page", "pricelist");

		model.addAttribute("carWashPaginator", carWashService.getCarWashTypePaginator(pageNumber,order,ascing));
		model.addAttribute("refuelingPaginator", refuelingService.getPaginatorRefuelingTypes(pageNumber,order,ascing));

		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    String name = user.getUsername(); //get logged in username
		    
		    Client cli = clientService.getClientByEmail(name);
			model.addAttribute("isClient",cli);
			}
			catch (Exception e) {
				model.addAttribute("isClient", null);
				return "home:pricelist";
			}	
		return "home:pricelist";
	}
	
}


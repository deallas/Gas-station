package pl.noname.stacjabenzynowa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.noname.stacjabenzynowa.service.PrizeService;

@RequestMapping(value = "/admin/loyalty/prize")
@Controller
public class PrizeController {
	
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
		
		model.addAttribute("prizePaginator", prizeService.getPaginatorPrizes(pageNumber,order,ascing));
		
		return "loyalty:list:prize";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
}

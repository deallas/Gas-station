package pl.noname.stacjabenzynowa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.noname.stacjabenzynowa.service.PetrolService;

@RequestMapping(value = "/admin/monitoring/log")
@Controller
public class MonitoringLogController {

	@Autowired
	private PetrolService petrolService; 
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		
		model.addAttribute("petrolContainerLogPaginator", petrolService.getPaginatorPetrolContainerLogs(pageNumber,order,ascing));
		
		return "monitoring:list:containerlog";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
}

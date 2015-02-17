package pl.noname.stacjabenzynowa.web.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Bill;
import pl.noname.stacjabenzynowa.persistance.BillElement;
import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.service.BillService;
import pl.noname.stacjabenzynowa.service.CarWashService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.service.RefuelingService;
import pl.noname.stacjabenzynowa.web.form.CheckoutForm;

@RequestMapping(value = "/admin/bill")
@Controller
public class BillController {
	private Logger logger = LoggerFactory.getLogger(BillController.class);
	
	@Autowired
	private BillService billService; 
	
	@Autowired
	private BillService billElementService; 
	
	@Autowired
	private RefuelingService refuelingService;
	
	@Autowired
	private CarWashService carWashService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired 
	private DateTimeService dateTimeService;
	
	@Autowired
	private PetrolService petrolService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String list(Model model,
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		model.addAttribute("paginator", billService.getBillPaginator(pageNumber, order, ascing));
		model.addAttribute("page","bill");
		
		return "bill:list";
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String billElementsShow(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backbill");
		
		Bill bill = billService.getBillById(id);
		
		if(bill == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.billelements.not_found", null, request.getLocale()));
			return "redirect:/admin/bill/";
		}
		
		List<BillElement> be = billElementService.getBillElementsByBillId(id);
		if(be!=null){
			model.addAttribute("be",be);
		}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/bill/";
		}
		return "bill:show";
	}
	
	@RequestMapping(value="/checkout", method = RequestMethod.GET)
	public String checkout(Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes)
	{	
		model.addAttribute("page","backbill");
		
		model.addAttribute("refuelingTypes",refuelingService.getRefuelingTypes());
		model.addAttribute("washTypes",carWashService.getCarWashTypes());
		model.addAttribute("containers",petrolService.getPetrolContainers());
		model.addAttribute("checkoutForm",new CheckoutForm());
		
		return "bill:checkout";
	}
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public String clientAdd(@ModelAttribute("checkoutForm") @Valid CheckoutForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		
		model.addAttribute("page","backbill");
		
		if(result.hasErrors())
		{
			return "bill:checkout";
		}
		
		Refueling ref = new Refueling();
		CarWash wash = new CarWash();
		Date date = dateTimeService.getCurrentDate();
		
		Client client = clientService.getClientById(form.getId());
		Integer points = client.getPoints();
		
		if(form.getRefuelingType()>0){
			RefuelingType reft = refuelingService.getRefuelingTypeById(form.getRefuelingType());
			if(form.getContainer()==0){
				redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
						messageSource.getMessage("controller.checkout.container.not_found", null, request.getLocale()));
				return "redirect:/admin/bill/checkout/";
			}
			PetrolContainer pet = petrolService.getPetrolContainerById(form.getContainer());
			ref.setClient(client);
			ref.setFuel(form.getFuel());
			ref.setCost(form.getFuel().multiply(reft.getCost()));
			ref.setRefuelingDate(date);
			ref.setRefuelingType(reft);
			ref.setPetrolContainer(pet);
			
			refuelingService.createRefueling(ref);
			
			points += form.getFuel().multiply(BigDecimal.valueOf(Long.valueOf(reft.getLoyalityPoints()))).intValue();
		}
		
		if(form.getWashType()>0){
			CarWashType cwt = carWashService.getCarWashTypeById(form.getWashType());
			wash.setCarWashType(cwt);
			wash.setDateBeginWash(date);
			wash.setDateEndWash(date);
			
			carWashService.createCarWash(wash);
			
			points += wash.getCarWashType().getLoyalityPoints();
		}

		BigDecimal total = BigDecimal.ZERO;
		if(ref!=null){
			total = total.add(ref.getCost());
		}
		if(wash!=null){
			total = total.add(wash.getCarWashType().getCost());
		}
		if(total.equals(BigDecimal.ZERO)){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.checkout.empty", null, request.getLocale()));
			return "redirect:/admin/bill/checkout/";
		}
		Bill bill = new Bill();
		bill.setClient(client);
		bill.setInvoiceDate(date);
		bill.setTotal(total);
		bill.setAmount(ref.getFuel());
		bill.setVat(total.multiply(BigDecimal.valueOf(22.0).divide(BigDecimal.valueOf(122.0),RoundingMode.HALF_EVEN)));
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername();
		bill.setEmployee(employeeService.getEmployeeByPeselOrEmail(name));
		
		billService.createBill(bill);
		
		BillElement be = new BillElement();
		be.setCarWash(wash);
		be.setRefueling(ref);
		be.setBill(bill);
		
		billService.createBillElement(be);
		
		//dodawanie punktów
		client.setPoints(points);
		clientService.updateClient(client);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.bill.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/bill/";
	}
}

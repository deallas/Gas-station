package pl.noname.stacjabenzynowa.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.web.form.ClientCompanyAddForm;
import pl.noname.stacjabenzynowa.web.form.ClientCompanyEditForm;
import pl.noname.stacjabenzynowa.web.form.ClientPeopleAddForm;
import pl.noname.stacjabenzynowa.web.form.ClientPeopleEditForm;

@RequestMapping(value = "/admin/client")
@SessionAttributes("accountEditForm")
@Controller
public class ClientController {
	
	@Autowired
	private AclService aclService; 
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Value("${auth.bcryptStrength}")
	private Integer bcryptStrength;
	
	@RequestMapping(value="/people/add", method = RequestMethod.GET)
	public String clientAdd(Model model) {
		
		model.addAttribute("page","backclientpeople");
		model.addAttribute("accountAddForm", new ClientPeopleAddForm());
		
		return "client:add:people";
	}
	
	@RequestMapping(value="/people/add", method = RequestMethod.POST)
	public String clientAdd(@ModelAttribute("accountAddForm") @Valid ClientPeopleAddForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		
		model.addAttribute("page","backclientpeople");
		
		if(result.hasErrors())
		{
			return "client:add:people";
		}
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		Client client = form.toClient();
		Date date = dateTimeService.getCurrentDate();
		client.setDateAdded(date);
		client.setRole(aclService.getRoleByName("USR"));
		clientService.createClient(client);
		
		ClientPeople clientPeople = form.toClientPeople();
		clientPeople.setClient(client);
		clientService.createClientPeople(clientPeople);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.client.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/client/people/add";
	}
	
	@RequestMapping(value="/company/add", method = RequestMethod.GET)
	public String clientCompanyAdd(Model model) {
		
		model.addAttribute("page","backclientcompany");
		model.addAttribute("accountAddForm", new ClientCompanyAddForm());
		
		return "client:add:company";
	}
	
	@RequestMapping(value="/company/add", method = RequestMethod.POST)
	public String clientCompanyAdd(@ModelAttribute("accountAddForm") @Valid ClientCompanyAddForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		
		model.addAttribute("page","backclientcompany");
		
		if(result.hasErrors())
		{
			return "client:add:company";
		}
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		Client client = form.toClient();
		Date date = dateTimeService.getCurrentDate();
		client.setDateAdded(date);
		client.setRole(aclService.getRoleByName("USR"));
		clientService.createClient(client);
		
		ClientCompany clientCompany = form.toClientCompany();
		clientCompany.setClient(client);
		clientService.createClientCompany(clientCompany);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.client.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/client/company/add";
	}
	
	@RequestMapping(value="/people/show/{id}", method = RequestMethod.GET)
	public String clientPeopleShow(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backclientpeople");
		Client client = clientService.getClientById(id);
		if(client == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/people/";
		}
		
		ClientPeople cp = clientService.getClientPeopleByClient(client);
		if(cp != null){
			model.addAttribute("cli",cp);
		}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/people/";
		}
		return "client:show:people";
	}
	
	@RequestMapping(value="/company/show/{id}", method = RequestMethod.GET)
	public String clientCompanyShow(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backclientcompany");
		Client client = clientService.getClientById(id);
		if(client == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/company/";
		}
		
		ClientCompany cc = clientService.getClientCompanyByClient(client);
		if(cc != null){
			model.addAttribute("cli",cc);
		}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/company/";
		}
		return "client:show:company";
	}
	
	@RequestMapping(value="/people/delete/{id}", method = RequestMethod.GET)
	public String clientPeopleDelete(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Client client = clientService.getClientById(id);
		
		if(client == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/people/";
		}
		
		ClientPeople cp = clientService.getClientPeopleByClient(client);
		if(cp != null){
			clientService.deleteClientPeople(cp);
			clientService.deleteClient(client);
			redirectAttributes.addFlashAttribute("INFO_MESSAGE",
					messageSource.getMessage("controller.client.delete_ok", null, request.getLocale()));
		}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
	    }
		
		return "redirect:/admin/client/people/";
	}
	
	@RequestMapping(value="/company/delete/{id}", method = RequestMethod.GET)
	public String clientCompanyDelete(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Client client = clientService.getClientById(id);
		
		if(client == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/company/";
		}
		ClientCompany cc = clientService.getClientCompanyByClient(client);
		if(cc != null){
			clientService.deleteClientCompany(cc);
			clientService.deleteClient(client);
			redirectAttributes.addFlashAttribute("INFO_MESSAGE",
					messageSource.getMessage("controller.client.delete_ok", null, request.getLocale()));
		}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
	    }
		return "redirect:/admin/client/company/";
	}
	
	@RequestMapping(value="/people/edit/{id}", method = RequestMethod.POST)
	public String clientPeopleEdit(@ModelAttribute("accountEditForm") @Valid ClientPeopleEditForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) { 
		
		model.addAttribute("page","backclientpeople");
	    
	    Client cli = clientService.getClientById(id);
	    
	    if(cli == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/people/";
		}
	    
	    ClientPeople clip = clientService.getClientPeopleByClient(cli);
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
	    
	    if(clip != null){
	    	form.setClient(cli);
	    	form.setClientPeople(clip);
			if(form.getPassword() != null && !form.getPassword().isEmpty()){
				form.setPassword(passwordEncoder.encode(form.getPassword()));
			}else{
				form.setPassword(cli.getPassword());
			}
	    }else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			
			return "redirect:/admin/client/people/";
	    }
	    
		if(result.hasErrors())
		{
			return "client:edit:people";
		}
		
		clientService.updateClient(form.toClient());
		clientService.updateClientPeople(form.toClientPeople());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.client.edit_ok", null, request.getLocale()));
		
		return "redirect:/admin/client/people/";
	}
	
	@RequestMapping(value="/people/edit/{id}", method = RequestMethod.GET)
	public String clientPeopleEdit(@PathVariable(value="id") Integer id, 
			Model model) {
		
		model.addAttribute("page","backclientpeople");
	    
	    Client cli = clientService.getClientById(id);
		ClientPeople clip= clientService.getClientPeopleByClient(cli);

		if(clip != null){
			ClientPeopleEditForm clientForm = new ClientPeopleEditForm();
			clientForm.populateByClientPeople(clip);

			model.addAttribute("accountEditForm", clientForm);
			return "client:edit:people";
		}else{
				return "redirect:/admin/client/people/";
		}
	}
	
	@RequestMapping(value="/company/edit/{id}", method = RequestMethod.POST)
	public String clientCompanyEdit(@ModelAttribute("accountEditForm") @Valid ClientCompanyEditForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) { 
		
		model.addAttribute("page","backclientcompany");
	    
	    Client cli = clientService.getClientById(id);
	    
	    if(cli == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			return "redirect:/admin/client/people/";
		}
	    
	    ClientCompany clic = clientService.getClientCompanyByClient(cli);
	    
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
	    
    	if(clic != null){
    		form.setClient(cli);
    		form.setClientCompany(clic);
			if(form.getPassword() != null && !form.getPassword().isEmpty()){
				form.setPassword(passwordEncoder.encode(form.getPassword()));
			}else{
				form.setPassword(cli.getPassword());
			}
    	}else{
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.client.not_found", null, request.getLocale()));
			
			return "redirect:/admin/client/company/";
    	}
	    
		if(result.hasErrors())
		{	
			return "client:edit:company";
		}
		
		clientService.updateClient(form.toClient());
		clientService.updateClientCompany(form.toClientCompany());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.client.edit_ok", null, request.getLocale()));
		
		return "redirect:/admin/client/people/";
	}
	
	@RequestMapping(value="/company/edit/{id}", method = RequestMethod.GET)
	public String clientCompanyEdit(@PathVariable(value="id") Integer id, 
			Model model) {
		
		model.addAttribute("page","backclientcompany");
	    
	    Client cli = clientService.getClientById(id);
		ClientCompany clic= clientService.getClientCompanyByClient(cli);

		if(clic != null){
			ClientCompanyEditForm clientForm = new ClientCompanyEditForm();
			clientForm.populateByClientCompany(clic);

			model.addAttribute("accountEditForm", clientForm);
			return "client:edit:company";
		}else{
				return "redirect:/admin/client/company/";
		}
	}
		
	@RequestMapping(value="/people/", method = RequestMethod.GET)
	public String clientPeopleList(Model model, 
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		model.addAttribute("page","people");
		model.addAttribute("clientPeoplePaginator", clientService.getPaginatorClientPeople(pageNumber, order, ascing));
		return "client:list:people";
	}
	
	@RequestMapping(value="/company/", method = RequestMethod.GET)
	public String clientCompanyList(Model model, 
			   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			   @RequestParam(value = "order", required = false) String order,
			   @RequestParam(value = "ascing", required = false) Boolean ascing)
	{
		model.addAttribute("page","company");
		model.addAttribute("clientCompanyPaginator", clientService.getPaginatorClientCompany(pageNumber, order, ascing));
		return "client:list:company";
	}
	
}


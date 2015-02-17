package pl.noname.stacjabenzynowa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.web.form.AccountClientCompanyEditForm;
import pl.noname.stacjabenzynowa.web.form.AccountClientPeopleEditForm;
import pl.noname.stacjabenzynowa.web.form.PasswordEmployeeEditForm;

@RequestMapping(value="/client/account/")
@Controller
public class ClientAccountController 
{
	@Autowired
	private AclService aclService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Value("${auth.bcryptStrength}")
	private Integer bcryptStrength;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String ClientAccount(ModelMap model) 
	{ 
		model.addAttribute("page","profile");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client cli = clientService.getClientByEmail(name);
	    
	    model.addAttribute("client",cli);
	    
	    ClientPeople clientPerson = clientService.getClientPeopleByClient(cli);
	    
	    if(clientPerson != null) {
	    model.addAttribute("clientPerson",clientPerson);
	    } else {
	    	ClientCompany clientCompany = clientService.getClientCompanyByClient(cli);
	    	model.addAttribute("clientCompany",clientCompany);
	    }
	    return "clientpanel:account";
	}
	
	@RequestMapping(value={"/editpassword"}, method = RequestMethod.GET)
	public String clientEditpassword(Model model) {
		
		model.addAttribute("page","password");
		model.addAttribute("client", 1);
		
		model.addAttribute("accountPasswordEditForm", new PasswordEmployeeEditForm());
		return "account:edit:password";
	}
	
	@RequestMapping(value="/editpassword", method = RequestMethod.POST)
	public String clientEditpassword(@ModelAttribute("accountPasswordEditForm") @Valid PasswordEmployeeEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) { 
		
		model.addAttribute("page","password");
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client cli = clientService.getClientByEmail(name);
		
	    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
	    
	    if(!passwordEncoder.matches(form.getOldPassword(), cli.getPassword())){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.editpassword.oldpassword_error", null, request.getLocale()));		
			return "redirect:/client/editpassword";
	    }
	    
	    if(result.hasErrors()){
			return "account:edit:password";
	    }
	    
	    if(!form.getPassword().isEmpty()){  
            cli.setPassword(passwordEncoder.encode(form.getPassword())); 
        }
		
		clientService.updateClient(cli);
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.editpassword.edit_ok", null, request.getLocale()));
		
		return "redirect:/client/account/";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String clientEdit(Model model) {
		
		model.addAttribute("page","editaccount");
		model.addAttribute("client", 1);
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client cli = clientService.getClientByEmail(name);
		ClientPeople clip= clientService.getClientPeopleByClient(cli);

		if(clip != null){
			AccountClientPeopleEditForm accForm = new AccountClientPeopleEditForm();
			accForm.populateByClientPeople(clip);

			model.addAttribute("accountEditForm", accForm);
			return "account:edit";
		}else{
			ClientCompany clic = clientService.getClientCompanyByClient(cli);
			if(clic != null){
				AccountClientCompanyEditForm accForm = new AccountClientCompanyEditForm();
				accForm.populateByClientCompany(clic);
			
				model.addAttribute("company",1);
				model.addAttribute("accountEditForm", accForm);
				return "account:edit";
			}else{
				return "redirect:/client/account";
			}
		}
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String clientEdit(@ModelAttribute("accountEditForm") @Valid Object form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) { 
		
		model.addAttribute("page","editaccount");
		model.addAttribute("client", 1);
   	   
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    
	    Client cli = clientService.getClientByEmail(name);
	    
	    if(cli == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.account.not_found", null, request.getLocale()));
			return "redirect:/client/";
		}
	    
	    ClientPeople clip = clientService.getClientPeopleByClient(cli);
	    ClientCompany clic = null;
	    
	    AccountClientPeopleEditForm accPeople = null;
	    AccountClientCompanyEditForm accCompany = null;
	    
	    if(clip != null){
	    	accPeople = (AccountClientPeopleEditForm) form;
	    	accPeople.setClient(cli);
	    	accPeople.setClientPeople(clip);
	    }else{
	    	clic = clientService.getClientCompanyByClient(cli);
	    	if(clic != null){
	    		accCompany = (AccountClientCompanyEditForm) form;
	    		accCompany.setClient(cli);
	    		accCompany.setClientCompany(clic);
	    	}
	    }
	    
		if(result.hasErrors())
		{
			if(clic != null){
				model.addAttribute("company",1);
			}
			return "account:edit";
		}

		if(clip != null){
			clientService.updateClient(accPeople.toClientPeople().getClient());
			clientService.updateClientPeople(accPeople.toClientPeople());
		}
		
		if(clic != null){
			clientService.updateClient(accCompany.toClientCompany().getClient());
			clientService.updateClientCompany(accCompany.toClientCompany());
		}
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.account.edit_ok", null, request.getLocale()));
		
		return "redirect:/client/account/";
	}
}

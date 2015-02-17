package pl.noname.stacjabenzynowa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.web.form.AccountEmployeeEditForm;
import pl.noname.stacjabenzynowa.web.form.PasswordEmployeeEditForm;

@RequestMapping(value="/admin/account/")
@Controller
@SessionAttributes({"accountPasswordEditForm"})
public class EmployeeAccountController 
{
	private Logger logger = LoggerFactory.getLogger(EmployeeAccountController.class);
	
	@Autowired
	private AclService aclService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String account(ModelMap model, HttpServletRequest request) 
	{ 
		model.addAttribute("page","profile");
		model.addAttribute("employee",request.getAttribute("loggedEmp"));
		
		return "emppanel:account";
	}
	
	@RequestMapping(value={"/editpassword"}, method = RequestMethod.GET)
	public String editpassword(Model model, HttpServletRequest request) 
	{
		Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
		PasswordEmployeeEditForm form = new PasswordEmployeeEditForm();
		form.setEncryptedPassword(loggedEmp.getPassword());
		
		model.addAttribute("page","password");
		model.addAttribute("accountPasswordEditForm", form);
		
		return "account:edit:password";
	}
	
	@RequestMapping(value="/editpassword", method = RequestMethod.POST)
	public String editpassword(@ModelAttribute("accountPasswordEditForm") @Valid PasswordEmployeeEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) 
	{ 
		model.addAttribute("page","password");
		
		if(result.hasErrors())
		{
			logger.info("Edit password failed");
			for(ObjectError err : result.getAllErrors()){
				logger.debug(err.toString());
			}
			return "account:edit:password";
		}
		
		Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
		Employee emp = employeeService.getEmployeeById(loggedEmp.getId()); // STUPID FIX !!!!!!!!!!!!!!!!!!!!
		
		employeeService.editPassword(emp, form.getPassword());
		
		logger.debug("Change password success");
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.editpassword.edit_ok", null, request.getLocale()));
		
		return "redirect:/admin/account/";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(Model model, HttpServletRequest request) {
		
		model.addAttribute("page","editaccount");
		
	    Employee emp = (Employee)request.getAttribute("loggedEmp");
		
		AccountEmployeeEditForm form = new AccountEmployeeEditForm();
		form.populateByEmployee(emp);
		
		model.addAttribute("accountEditForm", form);
		
		return "account:edit";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("accountEditForm") @Valid AccountEmployeeEditForm form,
			BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) 
	{ 	
		model.addAttribute("page","editaccount");
	   
		if(result.hasErrors())
		{
			logger.info("Edit password failed");
			for(ObjectError err : result.getAllErrors()){
				logger.debug(err.toString());
			}
			return "account:edit";
		}
		
		employeeService.updateEmployee(form.toEmployee());
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.account.edit_ok", null, request.getLocale()));
		
		return "redirect:/admin/account/";
	}
}

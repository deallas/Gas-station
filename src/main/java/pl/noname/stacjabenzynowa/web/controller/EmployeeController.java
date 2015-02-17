package pl.noname.stacjabenzynowa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.EmployeeService;
import pl.noname.stacjabenzynowa.web.form.EmployeeAddForm;
import pl.noname.stacjabenzynowa.web.form.EmployeeEditForm;
import pl.noname.stacjabenzynowa.web.paginator.EmployeeOptions;

@RequestMapping(value = "/admin/employee")
@Controller
public class EmployeeController 
{	
	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private AclService aclService; 
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String empList(Model model, 
						   @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
						   @RequestParam(value = "order", required = false) String order,
						   @RequestParam(value = "ascing", required = false) Boolean ascing) 
	{
		model.addAttribute("paginator", employeeService.getPaginatorEmployees(pageNumber, order, ascing));
		model.addAttribute("aclService", aclService);
		
		return "employee:list";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String empList(Model model,
			   			   @RequestParam("opt-orders[]") String[] orders,
			   			   @RequestParam("opt-order") String order,
			   			   @RequestParam("opt-items") Integer items,
			   			   @RequestParam("opt-ascing") Boolean ascing,
			   			   @RequestParam(value="reset", required=false) String reset)
	{
		EmployeeOptions paginatorOptions = employeeService.getEmployeePaginator();
        if(reset != null) {
        	paginatorOptions.clearPaginatorOptions();
        } else {
        	paginatorOptions.setPaginatorOptions(orders, order, items, ascing);              
        }
		
		return "redirect:/admin/employee/";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String empAdd(@Valid EmployeeAddForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backemployee");
		
	    Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
		
		if(result.hasErrors())
		{
			logger.info("Add employee failed");
			for(ObjectError err : result.getAllErrors()){
				logger.debug(err.toString());
			}
			model.addAttribute("roles", aclService.getRoleChildrenByRoleId(loggedEmp.getRole().getId()));
			return "employee:add";
		}
		Integer roleId = new Integer(String.valueOf(form.getRoleId()));
		form.setRole(aclService.getRoleById(roleId));
		form.setPassword(aclService.encodePassword(form.getPassword()));
		employeeService.createEmployee(form.toEmployee());
		
		logger.info("Add employee " + form.getName() + " " + form.getSurname() + " success");
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.employee.add_ok", null, request.getLocale()));
		
        return "redirect:/admin/employee/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String empAdd(HttpServletRequest request,
			Model model,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backemployee");
		
		Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
	
		model.addAttribute("roles", aclService.getRoleChildrenByRoleId(loggedEmp.getRole().getId()));
		model.addAttribute("employeeAddForm", new EmployeeAddForm());
		return "employee:add";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.POST)
	public String empEdit(@Valid EmployeeEditForm form,
    		BindingResult result,
    		HttpServletRequest request,
    		Model model,
    		RedirectAttributes redirectAttributes,
    		@PathVariable(value="id") Integer id) {
		model.addAttribute("page","backemployee");
	    
		Employee emp = employeeService.getEmployeeById(id);
		if(emp == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.not_found", null, request.getLocale()));
			return "redirect:/admin/employee/";
		}
		
		Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
		
		if(result.hasErrors())
		{
			logger.info("Edit employee failed");
			for(ObjectError err : result.getAllErrors()){
				logger.debug(err.toString());
			}
			model.addAttribute("roles", aclService.getRoleChildrenByRoleId(loggedEmp.getRole().getId()));
			return "employee:edit";
		}
		
		Integer roleId = new Integer(String.valueOf(form.getRoleId()));
		form.setRole(aclService.getRoleById(roleId));
		if(form.getPassword() != null && !form.getPassword().isEmpty()){
			form.setPassword(aclService.encodePassword(form.getPassword()));
		} else {
			form.setPassword(emp.getPassword());
		}
		employeeService.updateEmployee(form.toEmployee());
		
		logger.info("Edit employee " + form.getName() +" "+form.getSurname() + " success");
		
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
			messageSource.getMessage("controller.employee.edit_ok", null, request.getLocale()));
		
        return "redirect:/admin/employee/";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String empEdit(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("page","backemployee");
	    
	    Employee emp = employeeService.getEmployeeById(id);
	    
	    if(emp == null) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.not_found", null, request.getLocale()));
			return "redirect:/employee/";
		}
	    
	    Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
	    int roleIdLoggedEmp = loggedEmp.getRole().getId();
	    
	    if (!aclService.isChildRoleByRoleId(emp.getRole().getId(), roleIdLoggedEmp)) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.access_denied", null, request.getLocale()));	
			return "redirect:/admin/employee/";
	    }	    
	    
		EmployeeEditForm empForm = new EmployeeEditForm();
		empForm.populateByEmployee(emp);
			
		model.addAttribute("roles", aclService.getRoleChildrenByRoleId(roleIdLoggedEmp));
		model.addAttribute("employeeEditForm", empForm);
			
		return "employee:edit"; 
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String empDelete(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) 
	{
		Employee emp = employeeService.getEmployeeById(id);
		
		if(emp == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.not_found", null, request.getLocale()));
			return "redirect:/admin/employee/";
		}
		
		Employee loggedEmp = (Employee)request.getAttribute("loggedEmp");
		
		if (!aclService.isChildRoleByRoleId(emp.getRole().getId(), loggedEmp.getRole().getId())) {
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.access_denied", null, request.getLocale()));	
			return "redirect:/admin/employee/";
	    }	 
		
		employeeService.deleteEmployee(emp);
		redirectAttributes.addFlashAttribute("INFO_MESSAGE",
				messageSource.getMessage("controller.employee.delete_ok", null, request.getLocale()));
		return "redirect:/admin/employee/";
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String empShow(@PathVariable(value="id") Integer id, 
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) 
	{
		model.addAttribute("page","backemployee");
		Employee emp = employeeService.getEmployeeById(id);
		if(emp == null){
			redirectAttributes.addFlashAttribute("ERROR_MESSAGE",
					messageSource.getMessage("controller.employee.not_found", null, request.getLocale()));
			return "redirect:/admin/employee/";
		}
		model.addAttribute("employee", emp);
		
		return "employee:show";
	}
}


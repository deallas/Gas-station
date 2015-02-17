package pl.noname.stacjabenzynowa.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pl.noname.stacjabenzynowa.persistance.Employee;
import pl.noname.stacjabenzynowa.service.EmployeeService;

public class LoggedEmployeeInterceptor implements HandlerInterceptor
{
	private Logger logger = LoggerFactory.getLogger(LoggedEmployeeInterceptor.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	private Employee loggedEmp;
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object obj, 
								Exception exc) throws Exception 
	{	
	}

	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse res,
						   Object obj, 
						   ModelAndView model) throws Exception 
	{	
		model.getModelMap().addAttribute("loggedEmp", loggedEmp);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object obj) throws Exception 
	{
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername();
	   
	    try {
	    	loggedEmp = employeeService.getEmployeeByPeselOrEmail(name);
	    	logger.debug("Logged employee '" + name + "' exists");
	    } catch(UsernameNotFoundException exc) {
	    	logger.debug("Logged employee '" + name + "' doesn't exists");
	    	response.sendRedirect(request.getContextPath() + "/login?error=3");
	    	return false;
	    }
	    
	    request.setAttribute("loggedEmp", loggedEmp);
	    
		return true;
	}

}

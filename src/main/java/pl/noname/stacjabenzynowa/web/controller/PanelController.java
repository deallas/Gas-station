package pl.noname.stacjabenzynowa.web.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptchaImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.BlockerService;
import pl.noname.stacjabenzynowa.service.PetrolService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PanelController 
{	
	private static final Logger logger = LoggerFactory.getLogger(PanelController.class);
 
	@Autowired
	private AclService aclService;
	
	@Autowired
	private BlockerService blockerService;
	
	@Autowired
	private ReCaptchaImpl reCaptcha;
	
	@Autowired
	private PetrolService petrolService;
	
	@Value("${blocker.captchaMinAttempts}")
	private Integer captchaMinAttempts;
	
	@Value("${blocker.maxAttempts}")
	private Integer maxAttempts;
	
	@Value("${blocker.banSeconds}")
	private Integer banSeconds;
	
	/* ----------------------------------------------------------- */
	
	public ReCaptchaImpl getReCaptcha() {
		return reCaptcha;
	}

	public void setReCaptcha(ReCaptchaImpl reCaptcha) {
		this.reCaptcha = reCaptcha;
	}
	
	/* ----------------------------------------------------------- */
	
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) 
	{
		List<PetrolContainer> containers = petrolService.getPetrolContainers();
		List<PetrolContainerMeasurement> pcmLevel = new ArrayList<PetrolContainerMeasurement>();
		List<PetrolContainerMeasurement> pcmPressure = new ArrayList<PetrolContainerMeasurement>();
		PetrolContainerMeasurement __pcm;
		for (PetrolContainer c : containers){
			__pcm = petrolService.getPetrolContainerMeasurementByContainerId(c.getId(), PetrolContainerMeasurement.Type.PETROL_LEVEL);
			if(__pcm!=null){
				pcmLevel.add(__pcm);
			}
			else
			{
				PetrolContainerMeasurement _pcm = new PetrolContainerMeasurement();
				_pcm.setValue(null);
				_pcm.setPetrolContainer(c);
				pcmLevel.add(_pcm);
			}
			
			__pcm = petrolService.getPetrolContainerMeasurementByContainerId(c.getId(), PetrolContainerMeasurement.Type.PRESSURE);
			if(__pcm!=null){
				pcmPressure.add(__pcm);
			}
			else
			{
				PetrolContainerMeasurement _pcm = new PetrolContainerMeasurement();
				_pcm.setValue(null);
				_pcm.setPetrolContainer(c);
				pcmPressure.add(_pcm);
			}
		}
		model.addAttribute("pcmLevel", pcmLevel);
		model.addAttribute("pcmPressure", pcmPressure);
		return "emppanel:index";
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String clientHome(Locale locale, Model model) 
	{
		return "redirect:/client/account/";
		/*
		model.addAttribute("client", 1);
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "clientpanel:index"; */
	}

	@RequestMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) Integer errorCode, Model model, HttpServletRequest servletRequest)
	{	
		BlockerAttempt ep = blockerService.getActiveAttempt(servletRequest.getRemoteAddr(), aclService.getResgroupByName("employee_panel"));
		BlockerAttempt cp = blockerService.getActiveAttempt(servletRequest.getRemoteAddr(), aclService.getResgroupByName("client_panel"));
		if(ep != null || cp != null) {
			int ep_attempts = 0, cp_attempts = 0;
			if(ep != null){
				ep_attempts = ep.getAttempts();
			}
			if(cp != null){
				cp_attempts = cp.getAttempts();
			}
			if(ep_attempts >= captchaMinAttempts) {
				model.addAttribute("recaptcha", true);
			}else if(cp_attempts >= captchaMinAttempts){
				model.addAttribute("recaptcha",true);
			}
			if(ep_attempts >= cp_attempts){
				model.addAttribute("attempts", ep_attempts);
			}else{
				model.addAttribute("attempts", cp_attempts);
			}
		} else {
			model.addAttribute("attempts", 0);
		}
		
	    if (errorCode != null)
	    {
	    	model.addAttribute("error", errorCode);
	    }
	    
	    model.addAttribute("banSeconds", banSeconds);
	    model.addAttribute("maxAttempts", maxAttempts);
	 
	    return "emppanel:login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, 
						 HttpServletRequest request, 
						 HttpServletResponse response)
	{
		String cookieName = "SPRING_SECURITY_REMEMBER_ME_COOKIE";
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
		response.addCookie(cookie);
		
		return "emppanel:login";
	}
	
	@RequestMapping(value="/denied", method = RequestMethod.GET)
	public String denied()
	{
		return "emppanel:denied";
	}
	
	@RequestMapping(value="/default", method = RequestMethod.GET)
	public String defaultAfterLogin(HttpServletRequest request)
	{
		if (request.isUserInRole("ROLE_EMP")) {
	            return "redirect:/admin/";
		}
		
		return "redirect:/";
	}
}

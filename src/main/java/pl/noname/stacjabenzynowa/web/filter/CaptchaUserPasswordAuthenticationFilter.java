package pl.noname.stacjabenzynowa.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.security.ClientUsernamePasswordAuthenticationToken;
import pl.noname.stacjabenzynowa.security.EmployeeUsernamePasswordAuthenticationToken;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.BlockerService;
import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.EmployeeService;

import com.google.common.base.Preconditions;

public class CaptchaUserPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	protected Logger logger = LoggerFactory.getLogger(CaptchaUserPasswordAuthenticationFilter.class);

	private static final String DEFAULT_RESPONSE_FIELD = "recaptcha_response_field";
	private static final String DEFAULT_CHALLENGE_FIELD = "recaptcha_challenge_field";

	@Autowired
	private AclService aclService;
	
	@Autowired
	private BlockerService blockerService;
	
	@Autowired
	private ReCaptcha reCaptcha;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmployeeService employeeService;

	@Value("${blocker.captchaMinAttempts}")
	private Integer captchaMinAttempts;
	
	private boolean postOnly = true;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		
		String userType;
		
		String username = obtainUsername(request);
		if (username == null) {
			username = "";
		}
		username = username.trim();

		try{
			employeeService.getEmployeeByPeselOrEmail(username);
			userType = "employee";
		}catch(UsernameNotFoundException ex){
			logger.debug("Not employee");
			userType = "client";
		}
		
		BlockerAttempt ep = blockerService.getActiveAttempt(request.getRemoteAddr(), aclService.getResgroupByName("employee_panel"));
		BlockerAttempt cp = blockerService.getActiveAttempt(request.getRemoteAddr(), aclService.getResgroupByName("client_panel"));

		if((ep != null && ep.getAttempts() >= captchaMinAttempts) || (cp != null && cp.getAttempts() >= captchaMinAttempts)) {
			try {
				Preconditions.checkNotNull(request.getParameter(DEFAULT_CHALLENGE_FIELD));
				Preconditions.checkNotNull(request.getParameter(DEFAULT_RESPONSE_FIELD));
			} catch(NullPointerException e) {
				throw new BadCredentialsException("exception.request.captcha.not_present");
			}
			
			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(request.getRemoteAddr(),
					(String) request.getParameter(DEFAULT_CHALLENGE_FIELD),
					(String) request.getParameter(DEFAULT_RESPONSE_FIELD));

			if (!reCaptchaResponse.isValid()) {
				if(userType.equals("employee") && ep.getAttempts() < blockerService.getNumberOfMaxAttempts()){
					blockerService.incrementAttempt(ep);
				}else if(userType.equals("client") && cp.getAttempts() < blockerService.getNumberOfMaxAttempts()){
					blockerService.incrementAttempt(cp);
				}
				throw new BadCredentialsException("exception.wrongCaptcha");
			}
		}
		
		String password = obtainPassword(request);

		if (password == null) {
			password = "";
		}

		UsernamePasswordAuthenticationToken authRequest;
		if(userType.equals("employee")){
			authRequest = new EmployeeUsernamePasswordAuthenticationToken(username, password);
		}else{
			authRequest = new ClientUsernamePasswordAuthenticationToken(username, password);
		}
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
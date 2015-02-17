package pl.noname.stacjabenzynowa.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.service.AclService;
import pl.noname.stacjabenzynowa.service.BlockerService;

@Component("clientAuthenticationProvider")
public class ClientAuthenticationProvider implements AuthenticationProvider 
{	
	private static final String RESGROUP_NAME = "client_panel";	
	private static final Logger logger = LoggerFactory.getLogger(ClientAuthenticationProvider.class);
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private BlockerService blockerService;
	
	@Autowired
	private AclService aclService;
	
	@Value("${auth.bcryptStrength}")
	private Integer bcryptStrength;
	
	/* ----------------------------------------------------------- */
	
	public void setClientDetailsService(
			ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}
	
	public void setBlockerService(BlockerService blockerService){
		this.blockerService = blockerService;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}

	public Integer getBcryptStrength() {
		return bcryptStrength;
	}

	public void setBcryptStrength(Integer bcryptStrength) {
		this.bcryptStrength = bcryptStrength;
	}

	/* ----------------------------------------------------------- */

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		logger.info("ClientAuthenticationProvider");
		logger.debug("Principal: " + authentication.getPrincipal().toString());

		UserDetails user;
		
		//ip blocker
		String ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
		
		logger.debug("IP Address: " + ip);
		
		AclResgroup resgroup = aclService.getResgroupByName(RESGROUP_NAME);
		logger.debug("Resgroup: " + resgroup.getName() + " [" + resgroup.getId() + "]");
		
		BlockerAttempt blockerAttempt = blockerService.getActiveAttempt(ip, resgroup);
		
		if(blockerAttempt != null) {
			logger.debug("BlockerAttempt is not null");
			if(blockerService.getNumberOfMaxAttempts() <= blockerAttempt.getAttempts()) {
				logger.info("IP is blocked");
				throw new BadCredentialsException("exception.auth.IpBlocked");
			}
		}
		
		try{
			user = clientDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
		} catch(UsernameNotFoundException ex) {
			logger.info("UsernameNotFoundException");
			
			if(blockerAttempt == null) {
				logger.debug("Add attempt");
				blockerService.addAttempt(ip, resgroup);
			} else {
				logger.debug("Increment attempt");
				blockerService.incrementAttempt(blockerAttempt);
			}
			
			throw new BadCredentialsException("exception.auth.InvalidCreadentials");
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptStrength);
		if(!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
			logger.info("Wrong password");
			
			if(blockerAttempt == null) {
				logger.debug("Add attempt");
				blockerService.addAttempt(ip, resgroup);
			} else {
				logger.debug("Increment attempt");
				blockerService.incrementAttempt(blockerAttempt);
			}
			
			throw new BadCredentialsException("exception.auth.InvalidCreadentials");
		}
		
		AccountStatusUserDetailsChecker status = new AccountStatusUserDetailsChecker();
		status.check(user);
		
		if(blockerAttempt != null) {
			blockerService.setAttemptExpired(blockerAttempt);
		}
		
		logger.info("Authorization successful");
		ClientUsernamePasswordAuthenticationToken token = new ClientUsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		return token;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
	    return (ClientUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

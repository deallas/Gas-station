package pl.noname.stacjabenzynowa.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2491362816567340656L;

	public EmployeeUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	public EmployeeUsernamePasswordAuthenticationToken(UserDetails user,
			Object object, Collection<? extends GrantedAuthority> authorities) {
		super(user,object,authorities);
	}

}

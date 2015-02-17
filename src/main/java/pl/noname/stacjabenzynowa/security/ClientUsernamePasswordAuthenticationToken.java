package pl.noname.stacjabenzynowa.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ClientUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958412061886693564L;

	public ClientUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
	}

	public ClientUsernamePasswordAuthenticationToken(UserDetails user,
			Object object, Collection<? extends GrantedAuthority> authorities) {
		super(user,object,authorities);
	}

}

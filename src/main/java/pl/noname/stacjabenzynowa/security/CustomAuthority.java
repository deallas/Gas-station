package pl.noname.stacjabenzynowa.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242881866430624148L;
	
	private String authority;
	
	public CustomAuthority(String authority){
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
	
	@Override
	public int hashCode(){
		return authority.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(!(obj instanceof CustomAuthority)) return false;
		return ((CustomAuthority) obj).getAuthority().equals(authority);
	}

}

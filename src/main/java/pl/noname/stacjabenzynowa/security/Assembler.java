package pl.noname.stacjabenzynowa.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {
	
	@Transactional(readOnly=true)
	User buildUserFromUserEntity(UserDetails userEntity){
		String username = userEntity.getUsername();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.isEnabled();
		boolean accountNotExpired = userEntity.isAccountNonExpired();
		boolean credentialsNonExpired = userEntity.isCredentialsNonExpired();
		boolean accountNonLocked = userEntity.isAccountNonLocked();
		
		User user = new User(username,password,enabled,accountNotExpired,credentialsNonExpired,accountNonLocked,userEntity.getAuthorities());
		return user;
	}
}

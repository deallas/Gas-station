package pl.noname.stacjabenzynowa.util;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import pl.noname.stacjabenzynowa.persistance.AclRole;
import pl.noname.stacjabenzynowa.persistance.AclRoleParent;
import pl.noname.stacjabenzynowa.security.CustomAuthority;

public class SBUtils {
	
	private static String PREFIX = "ROLE_";
	
	public static void getRoles(AclRole role, Set<CustomAuthority> authorities){
		authorities.add(new CustomAuthority(PREFIX + role.getName()));
		Set<AclRoleParent> pRoles = role.getRoles();
		if(pRoles != null){
			for(AclRoleParent pRole : pRoles){
				getRoles(pRole.getRoleParent(),authorities);
			}
		}
	}
	
	public static void isClient(Model model, HttpServletRequest request){
		if(request.isUserInRole("ROLE_USR")){
			model.addAttribute("client", "TRUE");
		}
		else{
			model.addAttribute("client", "FALSE");
		}
	}
}

package pl.noname.stacjabenzynowa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.service.ClientService;

@Service("clientDetailsService")
public class ClientDetailsService implements UserDetailsService
{
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private Assembler assemblerService;
	
	public void setEmployeeService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setAssemblerService(Assembler assemblerService) {
		this.assemblerService = assemblerService;
	}

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Client client = clientService.getClientByEmail(username);

		return assemblerService.buildUserFromUserEntity(client);
	}

}

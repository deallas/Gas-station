package pl.noname.stacjabenzynowa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.noname.stacjabenzynowa.email.EmailServiceInterface;
import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.persistance.ClientPeople;

import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.service.ClientService;
import pl.noname.stacjabenzynowa.service.dao.ClientCompanyDao;
import pl.noname.stacjabenzynowa.service.dao.ClientDao;
import pl.noname.stacjabenzynowa.service.dao.ClientPeopleDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.ClientCompanyOptions;
import pl.noname.stacjabenzynowa.web.paginator.ClientPeopleOptions;

@Service("clientService")
@Transactional(propagation=Propagation.REQUIRED)
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ClientPeopleDao clientPeopleDao;
	
	@Autowired
	private ClientCompanyDao clientCompanyDao;
	
	@Autowired
	private ClientPeopleOptions clientPeoplePaginatorOptions;
	
	@Autowired
	private ClientCompanyOptions clientCompanyPaginatorOptions;
	
	@Autowired
	private EmailServiceInterface emailService;

	/* ----------------------------------------------------------- */
	
	@Override
	public ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	@Override
	public ClientPeopleDao getClientPeopleDao() {
		return clientPeopleDao;
	}

	public void setClientPeopleDao(ClientPeopleDao clientPeopleDao) {
		this.clientPeopleDao = clientPeopleDao;
	}
	
	@Override
	public ClientCompanyDao getClientCompanyDao() {
		return clientCompanyDao;
	}

	public void setClientCompanyDao(ClientCompanyDao clientCompanyDao) {
		this.clientCompanyDao = clientCompanyDao;
	}
	
	public EmailServiceInterface getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceInterface emailService) {
		this.emailService = emailService;
	}
	
	/* ----------------------------------------------------------- */

	public ClientPeopleOptions getClientPeoplePaginatorOptions() {
		return clientPeoplePaginatorOptions;
	}

	public void setClientPeoplePaginatorOptions(
			ClientPeopleOptions clientPeoplePaginatorOptions) {
		this.clientPeoplePaginatorOptions = clientPeoplePaginatorOptions;
	}

	public ClientCompanyOptions getClientCompanyPaginatorOptions() {
		return clientCompanyPaginatorOptions;
	}

	public void setClientCompanyPaginatorOptions(
			ClientCompanyOptions clientCompanyPaginatorOptions) {
		this.clientCompanyPaginatorOptions = clientCompanyPaginatorOptions;
	}

	@Override
	public void createClient(Client client) {
		clientDao.saveOrUpdate(client);
		String subject = "Konto utworzone";
		String text = "Dziêkujemy za rejestracjê!\n\n" + "Twoje konto na stronie Stacja Benzynowa zosta³o utworzone poprawnie\n" +
				"Nazwa u¿ytkownika: " + client.getUsername() + "\n";
		try {
			emailService.sendEmail(client.getEmail(), subject, text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateClient(Client client) {
		clientDao.saveOrUpdate(client);
	}

	@Override
	public void deleteClient(Client client) {
		clientDao.delete(client);
		String subject = "Konto usuniête";
		String text = "Twoje konto " + client.getUsername() + " na stronie Stacja Benzynowa zosta³o usuniête\n";
		try {
			emailService.sendEmail(client.getEmail(), subject, text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Client getClientByEmail(String username) 
	{
		if(username.isEmpty()){
			throw new UsernameNotFoundException("exception.username.Empty");
		}
		Criterion crit = Restrictions.or(
				Restrictions.eq("email", username)
		);
		
		Client client;
		List<Client> cls = clientDao.findByCriteria(crit);
		if(cls.isEmpty()) {
			throw new UsernameNotFoundException("exception.username.NotFound");
		} else {
			client = cls.get(0);
		}
		
		return client;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Client getClientById(Integer id){
		return clientDao.getClientById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Client> getClients() {
		return clientDao.getClients();
	}

	/* ClientPeople */
	
	@Override
	@Transactional(readOnly=true)
	public ClientPeople getClientPeopleByClient(Client client) {
		return clientPeopleDao.getClientPeopleByClient(client);
	}

	@Override
	public void createClientPeople(ClientPeople clientPeople) {
		clientPeopleDao.saveOrUpdate(clientPeople);
	}

	@Override
	public void updateClientPeople(ClientPeople clientPeople) {
		clientPeopleDao.saveOrUpdate(clientPeople);
	}

	@Override
	public void deleteClientPeople(ClientPeople clientPeople) {
		clientPeopleDao.delete(clientPeople);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<ClientPeople> getPaginatorClientPeople(Integer pageNumber, String order, Boolean ascing)
	{
		AbstractOptions options = getClientPeoplePaginatorOptions().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getClientPeoplePaginatorOptions().setPaginatorOptions(options);
		
		HibernatePaginator<ClientPeople> paginator = clientPeopleDao.getPaginatorClientPeople(options);
		
		return paginator;
	}
	
	/* ClientCompany*/
	
	@Override
	@Transactional(readOnly=true)
	public ClientCompany getClientCompanyByClient(Client client) {
		return clientCompanyDao.getClientCompanyByClient(client);
	}

	@Override
	public void createClientCompany(ClientCompany clientCompany) {
		clientCompanyDao.saveOrUpdate(clientCompany);
	}

	@Override
	public void updateClientCompany(ClientCompany clientCompany) {
		clientCompanyDao.saveOrUpdate(clientCompany);
	}

	@Override
	public void deleteClientCompany(ClientCompany clientCompany) {
		clientCompanyDao.delete(clientCompany);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<ClientCompany> getPaginatorClientCompany(Integer pageNumber, String order, Boolean ascing)
	{
		AbstractOptions options = getClientCompanyPaginatorOptions().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getClientCompanyPaginatorOptions().setPaginatorOptions(options);
		
		HibernatePaginator<ClientCompany> paginator = clientCompanyDao.getPaginatorClientCompany(options);
		
		return paginator;
	}

}

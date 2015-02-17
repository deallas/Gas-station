package pl.noname.stacjabenzynowa.email;

import javax.mail.MessagingException;


public interface EmailServiceInterface {
	
	void sendEmail(String recipient, String subject, String text) throws MessagingException;
}

package pl.noname.stacjabenzynowa.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailServiceImpl implements EmailServiceInterface {

	@Override
	public void sendEmail(String recipient, String subject, String text) throws MessagingException {
		
		Properties props = System.getProperties();
	    props.put("mail.smtp.starttls.enable", true); // added this line
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "stacja.benzynowa.k3");
	    props.put("mail.smtp.password", "stacja12345");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", true);
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setJavaMailProperties(props);
		sender.setPassword("stacja12345");
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setFrom(new InternetAddress("stacja.benzynowa.k3"));
		helper.setText(text);

		sender.send(message);
	}

}

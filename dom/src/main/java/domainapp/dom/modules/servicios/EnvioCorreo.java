package domainapp.dom.modules.servicios;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.AbstractFactoryAndRepository;

import domainapp.dom.modules.servicios.CorreoException;

@DomainService

public class EnvioCorreo extends AbstractFactoryAndRepository {
	
	
	public static String send(String mensaje, String asunto) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthentication("plusceltesis@gmail.com", "pluscel2015");
			email.setSSLOnConnect(true);
			email.setFrom("plusceltesis@gmail.com", "Pluscel");
			email.setSubject(asunto);
			email.setMsg(mensaje);			
			email.addTo("bossoar@gmail.com","plusceltesis@gmail.com");
			return email.send();
		} catch (EmailException e) {
			throw new CorreoException(e.getMessage(), e);
		}
			
	}
}

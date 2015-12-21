/*
 * This is software for administration and management of mobile equipment repair
 *
 * Copyright ( C ) 2015 , Pluscel
 *
 * This program is free software ; you can redistribute it and / or
 * Modify it under the terms of the GNU General Public License
 * As published by the Free Software Foundation ; either version 2
 * Of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * But WITHOUT ANY WARRANTY; without even the implied warranty
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. Boil
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * Along with this program ; if not, write to the Free Software
 *
 *
 * Foundation , Inc. , 51 Franklin Street, Fifth Floor , Boston, MA 02110-1301 , USA .
 */
package domainapp.dom.modules.servicios;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.AbstractFactoryAndRepository;

import domainapp.dom.modules.servicios.CorreoException;

@DomainService

public class EnvioCorreo extends AbstractFactoryAndRepository {
	
	public static String send(String destinatarios, String asunto, String mensaje) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthentication("plusceltesis@gmail.com", "pluscel2015");
			email.setSSLOnConnect(true);
			email.setFrom("plusceltesis@gmail.com", "Pluscel");
			email.setSubject(asunto);
			email.setMsg(mensaje);			
			/*email.addTo("bossoar@gmail.com","nachocartes@gmail.com","plusceltesis@gmail.com");*/
			email.addTo(destinatarios);
			return email.send();
		} catch (EmailException e) {
			throw new CorreoException(e.getMessage(), e);
		}
			
	}
}

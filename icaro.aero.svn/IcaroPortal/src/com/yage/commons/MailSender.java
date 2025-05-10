/*
 * Created on Nov 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.commons;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author martha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MailSender {

	public static boolean send(String smtpServer,int smtpPort,String to,String from,String subject,String bodyHtml) throws Exception {
			boolean send = false;
			
            // Create a mail session
			Properties props = System.getProperties();
            props.put("mail.smtp.port", ""+smtpPort);
			props.put("mail.smtp.host", smtpServer);
			Session session = Session.getDefaultInstance(props, null);

			// -- Create a new message --
			Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);
            
			MimeMultipart content = new MimeMultipart("alternative");
			MimeBodyPart html = new MimeBodyPart();
			html.setContent(bodyHtml, "text/html");
			content.addBodyPart(html);
			msg.setContent(content);


			//msg.setText(body);

			// -- Set some other header information --
			//msg.setHeader("X-Mailer", "LOTONtechEmail");
			msg.setSentDate(new Date());

			// -- Send the message --
			Transport.send(msg);
			send = true;
			return send;
		}

}

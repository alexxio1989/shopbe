package com.ws.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.ws.models.Acquisto;

@Component
public class SendEmail {
	
	public static void main(String[] args) {
		try {
			String template = "src/main/resources/corso-owner.html";
			String content = HtmlUtils.bufferHtml(template);
			send(getSession(), content, "alessiopinna.elis@gmail.com" , "Conferma di acquisto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendEmailAquisto(Acquisto acquisto) {

		Session session = getSession();

        sendEmailAcquirente(session, acquisto);
        sendEmailOwner(session, acquisto);
        sendEmailOwner1(session, acquisto);
	}


	public static void sendEmailAcquirente(Session session, Acquisto acquisto) {
		try {
			String html = "";
			html = HtmlUtils.getHtmlAquirenteAcquisto(acquisto);
			
			String emailTo = acquisto.getUtente().getEmail();
			send(session, html, emailTo , "Conferma di acquisto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendEmailOwner(Session session, Acquisto acquisto) {
		try {
			String html = "";
			html = HtmlUtils.getHtmlOwnerAcquisto(acquisto,true);
			
			String emailTo = "cristianmella.bacco@gmail.com";
			send(session, html, emailTo , "Notifica di acquisto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendEmailOwner1(Session session, Acquisto acquisto) {
		try {
			String html = "";
			html = HtmlUtils.getHtmlOwnerAcquisto(acquisto, false);
			String emailTo = "alessiopinna.elis@@gmail.com";
			send(session, html, emailTo , "Notifica di acquisto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void send(Session session, String html, String emailTo , String subject)
			throws MessagingException, UnsupportedEncodingException, AddressException {
		MimeMessage msg = new MimeMessage(session);
		// set message headers
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress("alessiopinna.elis@gmail.com", "Learn-it No Reply"));

		msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		msg.setSubject(subject, "UTF-8");

		Multipart multipart = new MimeMultipart("alternative");

		MimeBodyPart textPart = new MimeBodyPart();
		String text = "Hello, World";
		textPart.setText(text, "utf-8");

		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setContent(html, "text/html; charset=utf-8");

		multipart.addBodyPart(textPart);
		multipart.addBodyPart(htmlPart);
		msg.setContent(multipart);

		msg.setSentDate(new Date());

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
		System.out.println("Message is ready");
		Transport.send(msg);

		System.out.println("EMail Sent Successfully!!");
	}
	
	
	private static Session getSession() {
		System.out.println("SimpleEmail Start");
		String emailID = "alessiopinna.elis@gmail.com";

        final String password = "gkjgbwjspxewhxrp";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailID, password);
                    }
                });
		return session;
	}
	

}
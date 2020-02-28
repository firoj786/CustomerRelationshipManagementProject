package com.src.java.crm.utilities;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.src.java.crm.dto.CrmModule;

public class CrmUtility {
	public static String getModuleName(int moduleId, List<CrmModule> moduleList) {
		String moduleName = "";
		for(CrmModule gm :moduleList) {
			if(moduleId == gm.getId()) {
				moduleName = gm.getDescription();
			}
		}
			
		return moduleName;
	}
	
	public static String sendMail(String recipientAddress, String subject, String msgText) {
		String returnMsg = "Problem to send mail via Gmail SMTP...";
	
		// Sender's email ID needs to be mentioned
		String from = "gpssimcrm@gmail.com";// change accordingly
		final String username = "gpssimcrm";// change accordingly
		final String password = "bhu@123456";// change accordingly

		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(msgText);

			// Send message
			Transport.send(message);

			returnMsg = "Sent message successfully....";

		} catch (MessagingException e) {
			System.out.println("Problem to send Mail to [" + recipientAddress + "]");
		}
		
		return returnMsg;
	}
}

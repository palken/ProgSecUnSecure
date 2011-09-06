package no.ntnu.tdt4237;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {

	private static void sendMail(String fromMail, String toMail,String subject ,String text) {

	    Properties props = new Properties();
	    props.put("mail.smtp.host", Config.EMAIL_SMTP);
	    props.put("mail.from", fromMail);
	    Session session = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom();
	        msg.setRecipients(Message.RecipientType.TO,
	                          toMail);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(text);
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
    }
	
	public static void sendAvtivationCode(String toMail, String code)
	{
		sendMail(Config.EMAIL_SENDER_ADR, toMail, "Activation code for UnSecureBlog", "This is your activation code: "+code);
	}
}
package com.deloitte.Test_Deloitte.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
 * EmailServiceImpl class containing SMTP implementation.
 * */
@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSimpleMessage(String to, String subject, String body) throws MessagingException {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("mahashabdemanik@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		javaMailSender.send(simpleMailMessage);
	}
}
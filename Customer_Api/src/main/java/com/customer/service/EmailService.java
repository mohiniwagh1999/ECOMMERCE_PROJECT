package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
    @Autowired(required=true)
	private JavaMailSender mailsender;
	@Autowired
	private SpringTemplateEngine engine;
	
	public void sendResetPasswordEmail(String to,String resetUrl) throws MessagingException
	{
		MimeMessage mimeMessage = mailsender.createMimeMessage();
		MimeMessageHelper msg= new MimeMessageHelper(mimeMessage,true);
		msg.setTo(to);
		msg.setSubject("reset your password");
		
		Context context = new Context();
        context.setVariable("resetUrl", resetUrl);  // Pass reset link

        String htmlContent = engine.process("email.html", context);
        msg.setText(htmlContent, true);

        mailsender.send(mimeMessage);
		
		
	}

}

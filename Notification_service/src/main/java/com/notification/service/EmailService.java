package com.notification.service;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendOrderDeliveryEmail(String toEmail, String subject, String message, ByteArrayInputStream pdfStream) throws MessagingException {

	        // Create a MIME message
	        MimeMessage mimeMessage = mailSender.createMimeMessage();

	        // Use MimeMessageHelper to handle the attachment
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(message);

	        // Attach PDF
	        helper.addAttachment("order-invoice.pdf", new ByteArrayResource(pdfStream.readAllBytes()));

	        // Send the email
	        mailSender.send(mimeMessage);
	    }

}

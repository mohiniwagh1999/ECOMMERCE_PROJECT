package com.notification.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.notification.client.CustomerClient;
import com.notification.client.OrderClient;
import com.notification.request.Customer;
import com.notification.request.NotificationRequest;
import com.notification.service.EmailService;
import com.notification.service.PdfService;

import jakarta.mail.MessagingException;

@Component
public class NotificationController {
	  @Autowired
	    private OrderClient orderClient;

	    @Autowired
	    private PdfService pdfGeneratorService;

	    @Autowired
	    private EmailService emailService;

	    @Autowired
	    private CustomerClient customerClient;

	    @Scheduled(cron = "*/2 * * * * *")
	    public  void getOrderDataByDate() throws MessagingException {
	        List<NotificationRequest> ordersByDeliveryDate = orderClient.getOrdersByDeliveryDate();
	        for (NotificationRequest notificationRequest: ordersByDeliveryDate) {
	            ByteArrayInputStream bis = pdfGeneratorService.generateOrderInvoicePdf(notificationRequest);


	            Long customerId = notificationRequest.getOrders().getCustomerId();

	            Customer customerById = customerClient.findCustomerById(customerId);


	            String message = "Dear " + customerById.getName()+ ",\n\n" +
	                    "Your order with tracking ID  :" + notificationRequest.getOrders().getOrderTrackingNumber() + "\n has been delivering today.\n" +
	                    "Please collect your order.\n\nThank you!";
	            emailService.sendOrderDeliveryEmail(customerById.getEmail(),
	                    "Order Delivering today",
	                    message,
	                    bis);
	        }
	    }

}

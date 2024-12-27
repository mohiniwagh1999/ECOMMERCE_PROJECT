package com.notification.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.notification.request.NotificationRequest;

@FeignClient(url = "http://localhost:8085/",name = "ORDER-CLIENT")
public interface OrderClient {
	
	   @GetMapping("/orderData")
	    public List<NotificationRequest> getOrdersByDeliveryDate();

}

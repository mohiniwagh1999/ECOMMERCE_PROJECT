package com.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.notification.request.Customer;


@FeignClient(url = "http://localhost:8083/",name ="CUSTOMER-CLIENT")
public interface CustomerClient {
	
@GetMapping("/customer/{id}")
public Customer findCustomerById(@PathVariable("id") Long customerId);

}

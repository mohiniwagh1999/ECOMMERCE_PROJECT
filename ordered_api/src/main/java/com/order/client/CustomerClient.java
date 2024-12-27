package com.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.request.Customer;
import com.order.request.LoginRequest;
import com.order.response.CustomerResponse;

@FeignClient(url = "http://localhost:8083/",name = "CUSTOMER-CLIENT")
public interface CustomerClient {
	@PostMapping("/save")
	public CustomerResponse saveCustomer(Customer customer);
	
	@PostMapping("/findcutomer")
	public CustomerResponse findCustomerByEmailAndPassword(LoginRequest loginRequest);
	
	@PostMapping("/findemail/{email}")
	public boolean findCustomerByEmail(@PathVariable("email") String email);

}

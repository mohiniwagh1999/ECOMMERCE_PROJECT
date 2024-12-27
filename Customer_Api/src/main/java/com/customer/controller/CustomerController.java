package com.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Customer;
import com.customer.request.CustomerReq;
import com.customer.request.LoginReq;
import com.customer.request.ResetPasswordReq;
import com.customer.service.CustomerService;
import com.customer.service.EmailService;
import com.customer.service.PasswordResetService;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class CustomerController {
	@Autowired
	private CustomerService service;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordResetService passService;
	
	@PostMapping("/save")
	public Customer addCustomer(@RequestBody CustomerReq req)
	{
		return service.saveCustomer(req);
	}
	
	@GetMapping("/findcustomer")
	public Customer findCustomerByEmailAndPwd(@RequestBody LoginReq login)
	{
		String email = login.getEmail();
		String password = login.getPassword();
		Optional<Customer> customerByEmailAndPassword = service.getCustomerByEmailAndPassword(email, password);
		if(customerByEmailAndPassword.isPresent())
		{
			return customerByEmailAndPassword.get();
		}
		return null;
	}
	@PostMapping("/find/{email}")
	public boolean getCustomerByEmail(@PathVariable String email)
	{
		Optional<Customer> byemail = service.getByemail(email);
		
		String resetPassToken = passService.createResetPassToken(email);
		
		if(byemail.isPresent())
		{
			try
			{
				emailService.sendResetPasswordEmail(email,resetPassToken);
				return true;
			}
			catch(MessagingException e)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	@PostMapping("/reset")
	public boolean passwordReset(@RequestBody ResetPasswordReq req)
	{
		boolean customerByToken = service.findCustomerByToken(req);
		if(customerByToken)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		
		return service.findCustomerById(id);
		
		
	}
	

}

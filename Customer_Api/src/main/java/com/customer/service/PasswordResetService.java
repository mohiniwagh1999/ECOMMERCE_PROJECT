package com.customer.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Customer;
import com.customer.repo.CustomerRepo;

@Service
public class PasswordResetService {
	@Autowired
	private CustomerRepo repo;
	
	public String createResetPassToken(String email)
	{
		String token=UUID.randomUUID().toString();
		
		String resetUrl="http://localhost:4200/reset-password?token=" + token;
		
		Optional<Customer> byEmail = repo.findByEmail(email);
		if(byEmail.isPresent())
		{
			Customer customer = byEmail.get();
			 customer.setToken(token);
			 repo.save(customer );
			 return resetUrl;
		}
		return null;
	}

}

package com.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.Customer;
import com.customer.repo.CustomerRepo;
import com.customer.request.CustomerReq;
import com.customer.request.ResetPasswordReq;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo repo;
	
	public Customer saveCustomer(CustomerReq crequest)
	{
		Optional<Customer> byEmail = repo.findByEmail(crequest.getEmail());
		if(byEmail.isPresent())
		{
			return byEmail.get();
		}
		
		Customer customer=Customer.builder()
				.name(crequest.getName())
				.email(crequest.getEmail())
				.phoneNumber(crequest.getPhoneNumber())
				.build();
		return repo.save(customer);
		
		
	}
	
	public Optional<Customer> getCustomerByEmailAndPassword(String email,String Password)
	{
		return  repo.findByEmailAndPassword(email, Password);
	}
	public Optional<Customer> getByemail(String email)
	{
		return repo.findByEmail(email);
	}
	
	public boolean findCustomerByToken(ResetPasswordReq reset)
	{
		Optional<Customer> byToken = repo.findByToken(reset.getToken());
		if(byToken.isPresent())
		{
			Customer customer = byToken.get();
			customer.setPassword(reset.getNewPassword());
			customer.setToken(reset.getToken());
			Customer save = repo.save(customer);
			if(save.getId()!=null)
			{
				return true;
			}
			return false;
		}
		return false;
	}

	public Customer findCustomerById(Long id) {
		// TODO Auto-generated method stub
		Optional<Customer> byId = repo.findById(id);
		if(byId.isPresent())
		{
			return byId.get();
		}
		else
		{
			return null;
		}
	}

}

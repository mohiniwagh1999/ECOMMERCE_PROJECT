package com.customer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
	
	Optional<Customer> findByEmail(String email);
	
	Optional<Customer> findByEmailAndPassword(String email,String Password);
	Optional<Customer> findByToken(String token);

}

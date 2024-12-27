package com.address.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.entity.Address;
import com.address.repo.AddressRepo;
import com.address.request.AddressReq;

@Service
public class AddressService {
	@Autowired
	private AddressRepo repo;
	
	
	public Address saveAddress(AddressReq add)
	{
		Address address=Address.builder()
		.houseNo(add.getHouseNo())
		.street(add.getStreet())
		.city(add.getCity())
		.country(add.getCountry())
		.state(add.getState())
		.zipcode(add.getZipcode())
		.build();
		return repo.save(address);
	}

}

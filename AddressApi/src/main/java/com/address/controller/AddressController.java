package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.address.entity.Address;
import com.address.request.AddressReq;
import com.address.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@PostMapping("/address")
	public Address addAddress(@RequestBody AddressReq req)
	{
		return service.saveAddress(req);
	}

}

package com.order.client;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.order.response.AddressReponse;

@FeignClient(url = "http://localhost:8084/",name = "ADDRESS-CLIENT")
public interface AddressClient {
	@PostMapping("/address")
	AddressReponse saveAddress(Address address);

}

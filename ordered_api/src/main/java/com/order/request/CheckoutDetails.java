package com.order.request;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.order.entity.Orders;

import com.order.entity.OrderItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutDetails {

	private Customer customer;
	private OrderData orderData;
	private Address address;
	private List<OrderItems> orderItems;
	
	
	 
}

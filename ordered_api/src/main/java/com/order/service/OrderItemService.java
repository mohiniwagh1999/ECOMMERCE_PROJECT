package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.order.entity.OrderItems;
import com.order.repo.OrderItemRepo;
@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepo itemrepo;
	public OrderItems saveOrderItem(OrderItems orderitem)
	{
		return itemrepo.save(orderitem);
	}

}

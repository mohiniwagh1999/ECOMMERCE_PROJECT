package com.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Orders;
import com.order.repo.OrderRepo;
import com.order.request.OrderData;
@Service
public class OrderService {
	@Autowired
	private OrderRepo orepo;
	
	
	public Orders saveOrder(Orders order)
	{
		return orepo.save(order);
	}
	
	
	public Optional<Orders> findByRazorPayPaymentId(String orderId)
	{
		return orepo.findByRazorPayPaymentId(orderId);
	}
	
	public List<Orders> findByCustomerId(Long id)
	{
		return orepo.findByCustomerId(id);
	}

    public List<Orders> getOrdersByDeliveryDate(){
        LocalDate today = LocalDate.now();
        return orepo.findByDeliveryDate(today);
    }
	
	
	

}

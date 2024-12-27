package com.order.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.Orders;
import com.order.request.OrderData;

public interface OrderRepo extends JpaRepository<Orders,Long> {
	
	Optional<Orders> findByRazorPayPaymentId(String orderId);
	
	List<Orders>findByCustomerId(Long id);

	List<Orders> findByDeliveryDate(LocalDate today);
	

}

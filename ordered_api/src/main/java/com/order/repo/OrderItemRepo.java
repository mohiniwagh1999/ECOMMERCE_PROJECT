package com.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.order.entity.OrderItems;

public interface OrderItemRepo extends JpaRepository<OrderItems,Long> {

}

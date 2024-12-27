package com.order.response;

import java.util.List;

import com.order.entity.OrderItems;
import com.order.entity.Orders;
import com.order.request.OrderData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
	
	   private Orders orders;
	   private List<OrderItems> orderItems;


	public static NotificationResponse fromOrderData(Orders orders){
	return NotificationResponse.builder()
	        .orders(orders)
	        .orderItems(orders.getOrderItem())
	        .build();
	}

}

package com.order.response;

import com.order.entity.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponse {

	 private String orderTrackingNum;
	 private Long totalQuantity;
	 private String orderStatus;
	 
	 
	 public static DashboardResponse fromOrder(Orders order)
	 {
		 return DashboardResponse.builder()
				 .orderTrackingNum(order.getOrderTrackingNum())
				 .totalQuantity(order.getTotalQuantity())
				 .orderStatus(order.getOrderStatus())
				 .build();
		 
		
		 
		 
		 
	 }
}

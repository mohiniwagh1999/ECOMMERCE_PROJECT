package com.order.request;

import com.order.entity.OrderItems;
import com.order.entity.Orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderData {
	
	 private Long  totalQuantity;
     private Double totalPrice;
     
     

}

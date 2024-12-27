package com.notification.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
	
	private Orders orders;
	   private List<OrderItems> orderItems;

}

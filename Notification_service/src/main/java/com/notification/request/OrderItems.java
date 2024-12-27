package com.notification.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItems {
	
	    
		private Long id;
		private String imageUrl;
		private String name;
		private Double price;
		private Long quantity;
		private Long productId;

}

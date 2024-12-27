package com.notification.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
	
	 private Integer id;
	    private String orderTrackingNumber;
	    private String totalQuantity;
	    private Double totalPrice;
	    private String orderStatus;
	    private String razorPayPaymentId;

	    private LocalDate deliveryDate;

	    private Long customerId;

	    private Long addressId;
	    
	    
	    
	    
	    

}

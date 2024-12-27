package com.notification.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Customer {
	
	  private Long id;
	    private String name;
	    private String email;
	    private String password;
	    private Long phoneNumber;
	    private String token;

}

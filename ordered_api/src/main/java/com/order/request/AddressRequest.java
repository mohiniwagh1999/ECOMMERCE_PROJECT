package com.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
	private String houseNo;
	private String street;
	private String city;
	private String state;
	private String country;
	private Long zipcode;

}

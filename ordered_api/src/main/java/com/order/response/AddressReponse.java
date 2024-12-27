package com.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressReponse {
	private Long id;
	private String houseNo;
	private String city;
	private String street;
	private String state;
	private String country;
	private Long zipcode;
	

}

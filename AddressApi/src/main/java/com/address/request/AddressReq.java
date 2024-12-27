package com.address.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressReq {
	  private String houseNo;
	    private String street;
	    private String city;
	    private String state;
	    private Long zipcode;
	    private String country;

}

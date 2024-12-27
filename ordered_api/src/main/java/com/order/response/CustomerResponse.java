package com.order.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
	private Long id;
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private Long phno;
	
	

}

package com.order.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private Long phno;
	
}

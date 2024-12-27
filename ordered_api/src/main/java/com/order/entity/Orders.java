package com.order.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)

public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String OrderTrackingNum;
	private Long TotalQuantity;
	private Double TotalPrice;
	private String OrderStatus;
	
    @CreatedDate
	 @Column(updatable = false)
	private LocalDate orderCreationDate;
	 
	  @LastModifiedDate
	  @Column(insertable = false)
	private LocalDate orderUpdateDate;
	  
	  private String razorPayPaymentId;
	  
	  private Long customerId;
	  private Long addressId;
	  private LocalDate deliveryDate;
	  
	  @OneToMany(mappedBy="order",cascade= CascadeType.ALL)
	  private List<OrderItems> orderItem;
	

}

package com.customer.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReq {

    private String name;
    @Column(unique = true)
    private String email;
    private Long phoneNumber;

}

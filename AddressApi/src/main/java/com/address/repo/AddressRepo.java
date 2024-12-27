package com.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.address.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Long> {

}

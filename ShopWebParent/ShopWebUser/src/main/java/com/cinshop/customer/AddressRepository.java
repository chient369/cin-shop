package com.cinshop.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinshop.common.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	public Optional<Address> findAddressById(Integer custId);
}

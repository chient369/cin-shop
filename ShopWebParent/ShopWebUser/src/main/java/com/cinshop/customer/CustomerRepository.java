package com.cinshop.customer;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


	public Optional<Customer> findByEmail(String email);

	
	Optional<Customer> findCustomerByEmail(String email);

}

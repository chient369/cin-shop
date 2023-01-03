package com.cinshop.customer;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


	public Optional<Customer> findByEmail(String email);

	
	Optional<Customer> findCustomerByEmail(String email);
	
	@Query("select count(c) from Customer c where  c.email = ?1")
	Long countByEmail(String email);

}

package com.cinshop.admin.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
 
	@Query("select count(c) from Customer c where c.role = 'ROLE_USER'")
	public long countShopMember();
	@Query("select count(c) from Customer c where c.role = 'ROLE_GUEST'")
	public long countGuest();
	
	
	
}

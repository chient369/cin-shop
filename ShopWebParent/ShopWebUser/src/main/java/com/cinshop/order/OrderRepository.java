package com.cinshop.order;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Page<Order> findByCustomer(Customer customer,Pageable pageable);
	
	
}

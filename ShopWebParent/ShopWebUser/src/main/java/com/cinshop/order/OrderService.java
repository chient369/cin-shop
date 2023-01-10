package com.cinshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public Page<Order> findOrderByCustomerId(Integer id,Pageable pageable) {
		return repository.findByCustomer(new Customer(id),pageable);
	}
	
	public Order saveOrder(Order order) {
		return repository.save(order);
	}
	public Order updateOrder(Order order) {
		return repository.save(order);
	}
	public Order findOrderById(Integer id){
		return repository.findById(id).get();
	}
	
}

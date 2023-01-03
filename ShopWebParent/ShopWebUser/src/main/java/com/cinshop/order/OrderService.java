package com.cinshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findOrderByCustomerId(Integer id) {
		return repository.findByCustomer(new Customer(id));
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

package com.cinshop.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Order;

@Service
public class CustomerOrderService extends AbstractOrderService {

	public List<Order> findOrderByCustomerId(Integer id) {
		return orderService.findOrderByCustomerId(id);
	}
	public Order findOrderById(Integer id) {
		return orderService.findOrderById(id);
	}
	

}

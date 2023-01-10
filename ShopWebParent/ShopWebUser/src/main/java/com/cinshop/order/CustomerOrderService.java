package com.cinshop.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Order;

@Service
public class CustomerOrderService extends AbstractOrderService {

	public Page<Order> findOrderByCustomerId(Integer id,Pageable pageable) {
		return orderService.findOrderByCustomerId(id,pageable);
	}
	public Order findOrderById(Integer id) {
		return orderService.findOrderById(id);
	}
	

}

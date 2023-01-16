package com.cinshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.Order;

@Service
public class OrderServiceAdmin {

	@Autowired
	private OrderRepositoryAdmin repository;

	public Page<Order> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Order findOrderById(Integer id) {
		return repository.findByOrderId(id);
	}

	public Integer countPlacedOrder() {
		return repository.countPlacedOrder();
	}

	public Integer countOrder() {
		return repository.countOrder();
	}

	public Integer countDeliveringOrder() {
		return repository.countDeliveringOrder();
	}

	public Integer totalSales() {
		return repository.totalSales();
	}

	public Order updateOrder(Order order) {
		return repository.save(order);
	}
}

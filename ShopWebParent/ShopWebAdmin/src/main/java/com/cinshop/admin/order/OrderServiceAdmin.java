package com.cinshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Order> findByOrderNum(String orderNum, Pageable pageable) {
		return repository.findByOrderNum(orderNum, pageable);
	}

	public Page<Order> findByCustomerName(String custName, Pageable pageable) {
		return repository.findByCustomerName(custName, pageable);
	}

	public Page<Order> findByOrderStatus(String status, Pageable pageable){
		OrderStatus orderStatus = null;
		switch (status.toUpperCase()) {
		case "PAID":
			orderStatus = OrderStatus.PAID;
			break;

		case "PLACED":
			orderStatus =  OrderStatus.PLACED;
			break;

		case "PROCESSING":
			orderStatus =  OrderStatus.PROCESSING;
			break;

		case "DELIVERING":
			orderStatus =  OrderStatus.DELIVERING;
			break;

		case "COMPLETED":
			orderStatus =  OrderStatus.COMPLETED;
			break;
		}
		return repository.findByStatus(orderStatus, pageable);
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

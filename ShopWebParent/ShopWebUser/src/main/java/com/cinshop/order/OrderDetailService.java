package com.cinshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository repository;
	
	public List<OrderDetail> findOrderDetailsByProductId(Integer orderId){
		return repository.findByOrder(new Order(orderId));
	}
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		return repository.save(orderDetail);
	}
}

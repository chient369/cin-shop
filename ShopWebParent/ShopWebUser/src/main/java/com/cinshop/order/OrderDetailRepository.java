package com.cinshop.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	public List<OrderDetail> findByOrder(Order order);
}

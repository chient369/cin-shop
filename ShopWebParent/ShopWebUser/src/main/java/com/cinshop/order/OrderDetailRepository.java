package com.cinshop.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinshop.common.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}

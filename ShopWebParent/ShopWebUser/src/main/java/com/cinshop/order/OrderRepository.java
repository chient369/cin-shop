package com.cinshop.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinshop.common.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

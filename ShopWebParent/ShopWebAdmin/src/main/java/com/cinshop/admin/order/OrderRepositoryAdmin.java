package com.cinshop.admin.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.Order;

@Repository
public interface OrderRepositoryAdmin extends JpaRepository<Order, Integer> {

	Page<Order> findAll(Pageable pageable);

	@Query("select o from Order o where o.id =?1")
	Order findByOrderId(Integer id);

	@Query("select count(o) from Order o where o.status = 'PLACED'")
	public Integer countPlacedOrder();

	@Query("select count(o) from Order o")
	public Integer countOrder();

	@Query("select count(o) from Order o where o.status = 'DELIVERING'")
	public Integer countDeliveringOrder();
	
	@Query("select sum(o.total) from Order o ")
	public Integer totalSales();
	
	@Query("select o from Order o where o.orderNum = ?1")
	public Page<Order> findByOrderNum(String orderNum,Pageable pageable);
	
	@Query("select o from Order o where o.status = ?1")
	public Page<Order> findByStatus(OrderStatus status,Pageable pageable);
	
	@Query("select o from Order o where concat(o.customer.firstName,o.customer.lastName) like %?1%")
	public Page<Order> findByCustomerName(String custName,Pageable pageable);
	
	
}

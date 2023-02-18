package com.cinshop.order;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Page<Order> findByCustomer(Customer customer,Pageable pageable);

	@Query("select o from Order o left join OrderDetail od on o.id = od.id where o.customer.id = ?1 and od.product.productDetail.id = ?2")
	public List<Order> findOrderByCustomerAndProductId(Integer custId, Integer dId);
	
	
}

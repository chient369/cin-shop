package com.cinshop.admin.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Order;

@Repository
public interface OrderRepositoryAdmin  extends PagingAndSortingRepository<Order, Integer>{
	
	Page<Order> findAll(Pageable pageable);
	
	@Query("select o from Order o where o.id =?1")
	Order findByOrderId(Integer id);
	
	@Modifying
	@Query("update Order o set o.status = ?1 where o.id = ?2")
	public void updateOrderStatus(String status,Integer id);
	
	

}

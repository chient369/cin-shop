package com.cinshop.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Integer>{

	public List<CartItem> findByCustomer(Customer customer);
	public Optional<CartItem> findByCustomerAndProduct(Customer customer,Product product);
	
	@Modifying
	@Query("update CartItem c set c.quantity = ?3 where c.customer.id = ?1 and c.product.id = ?2 ")
	public void updateCartItem(Integer custId,Integer pId,Integer quantity);
	
	public void deleteByCustomer(Customer customer);
}

package com.cinshop.cart;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Product;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private CartItemRepository repository;

	public List<CartItem> findCartItemsByCustomerId(Integer custId) {
		List<CartItem> cartItems = repository.findByCustomer(new Customer(custId));
		Iterator<CartItem> it = cartItems.iterator();
		while (it.hasNext()) {
			cartItems.add(it.next());
		}
		return cartItems;
	}

	@Transactional
	public void saveCartItem(Integer custId, Integer pId, Integer quantity) {

		if (isExistCartItem(custId, pId)) {
			repository.save(new CartItem(new Customer(custId), new Product(pId), quantity));
		}

		repository.updateCartItem(custId, pId, quantity);
	}

	@Transactional
	public void removeItem(Integer custId, Integer pId) {
		repository.removeItem(custId, pId);;
	}

	public void deleteCartByCustomerid(Integer custId) {
		repository.deleteByCustomer(new Customer(custId));
	}

	private boolean isExistCartItem(Integer custId, Integer pId) {
		Optional<CartItem> cartItem = repository.findByCustomerAndProduct(new Customer(custId), new Product(pId));

		return cartItem.isEmpty() ? true : false;
	}
}

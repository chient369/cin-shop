package com.cinshop.cart;

import java.util.List;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Product;

import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractCartService {
	
	protected List<CartItem> cartItems;

	public AbstractCartService() {
		super();
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	// カート機能のメソッド
	public abstract List<CartItem> findCartItems();

	public abstract List<CartItem> addCartItem(Product product, Integer quantity);

	public abstract List<CartItem> updateCartItem(HttpServletRequest request);

	public abstract void deleteCart();
}

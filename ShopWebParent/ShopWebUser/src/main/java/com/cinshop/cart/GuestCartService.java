package com.cinshop.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class GuestCartService extends AbstractCartService {
	private HttpSession session;

//	public GuestCartServiceImpl(HttpSession session) {
//		super();
//		super.cartItems = new ArrayList<>();
//		this.session = session;
//	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
		//セッションにカート属性を作成
		if (session.getAttribute("cart") == null) {
			super.cartItems = new ArrayList<>();
			session.setAttribute("cart", cartItems);
		}
	}

	@Override
	public List<CartItem> findCartItems() {
		return (List<CartItem>) session.getAttribute("cart");
	}

	@Override
	public List<CartItem> addCartItem(Product product, Integer quantity) {
		CartItem cartItem = new CartItem(product, quantity);
		super.cartItems = (List<CartItem>) session.getAttribute("cart");

		if (cartItems.contains(cartItem)) {
			Integer existItemOfIndex = cartItems.indexOf(cartItem);
			CartItem existItem = cartItems.get(existItemOfIndex);
			existItem.setQuantity(existItem.getQuantity() + quantity);
		}else {
			cartItems.add(cartItem);
		}

		return cartItems;
	}

	@Override
	public List<CartItem> updateCartItem(HttpServletRequest request) {
		Map<String, String[]> param = request.getParameterMap();
		String[] productId = param.get("productId");
		String[] quantity = param.get("quantity");

		cartItems = (List<CartItem>) session.getAttribute("cart");
		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
		}
		cartItems.removeIf(item -> item.getQuantity() == 0);

		return cartItems;

	}

	@Override
	public void deleteCart() {
		this.session.removeAttribute("cart");
		super.cartItems.clear();

	}

	@Override
	public String toString() {
		return "GuestCartServiceImpl";
	}
	

}

package com.cinshop.cart;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Component
public class CustomerCartService extends AbstractCartService {
	private static Logger logger = LoggerFactory.getLogger(CustomerCartService.class);
	@Autowired
	private CartItemRepository repo;

	
	private Customer customer;

	public CustomerCartService() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		super.cartItems = repo.findByCustomer(this.customer);
	}

	@Override
	public List<CartItem> findCartItems() {
		return repo.findByCustomer(this.customer);
	}

	@Override
	public List<CartItem> addCartItem(Product product, Integer quantity) {
		CartItem cartItem = new CartItem(customer, product, quantity);
		repo.save(cartItem);

		logger.info("顧客{}がカートに商品を追加しました。", customer.getId());
		return findCartItems();
	}

	@Override
	public List<CartItem> updateCartItem(HttpServletRequest request) {

		// クライアントから受け取ったカートの情報
		Map<String, String[]> param = request.getParameterMap();
		String[] productId = param.get("productId");
		String[] quantity = param.get("quantity");

		cartItems = repo.findByCustomer(customer);
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			cartItem.setQuantity(Integer.parseInt(quantity[i]));
			if(cartItem.getQuantity() == 0) {
				repo.delete(cartItem);
				continue;
			}
			repo.save(cartItem);
		}
		// 数量が0の場合、削除する
		cartItems.removeIf(item -> item.getQuantity() == 0);
		
		logger.info("顧客{}がカートを更新した", customer.getId());
		return cartItems;
	}
	
	@Override
	@Transactional
	public List<CartItem> deleteCartItem(Integer pId) {
		repo.removeItem(this.customer.getId(), pId);
		logger.info("顧客{}がカートを削除した", this.customer.getId());
		return findCartItems();
	}

	@Override
	@Transactional
	public void deleteCart() {
		repo.deleteByCustomer(this.customer);
		super.cartItems.clear();
		logger.info("顧客{}がカートを削除した", this.customer.getId());
	}

	@Override
	public String toString() {
		return "CustomerCartService [customer=" + this.customer.getFullName() + "]";
	}
	

}

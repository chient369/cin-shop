package com.cinshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.cart.CustomerCartService;

@Component
public class OrderServiceFactory {

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private GuestOrderService guestOrderService;

	public AbstractOrderService getOrderService(AbstractCartService cartService) {
		if (cartService instanceof CustomerCartService) {
			customerOrderService.setCartService(cartService);
			return customerOrderService;
		} else {
			guestOrderService.setCartService(cartService);
			return guestOrderService;
		}
	}
}

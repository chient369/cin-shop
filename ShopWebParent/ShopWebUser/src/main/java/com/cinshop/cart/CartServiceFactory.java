package com.cinshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Customer;

import jakarta.servlet.http.HttpSession;

@Component
public class CartServiceFactory {
	
	@Autowired
	private GuestCartService guestCartServiceImpl;
	@Autowired
	private CustomerCartService customerCartServiceImpl;

	public AbstractCartService getCartService(Customer customer, HttpSession session) {
		if (customer == null) {
			guestCartServiceImpl.setSession(session);
			return guestCartServiceImpl;
		} else {
			customerCartServiceImpl.setCustomer(customer);
			return customerCartServiceImpl;
		}

	}

}

package com.cinshop;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Customer;
import com.cinshop.customer.CustomerService;
import com.cinshop.utility.Utility;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class AccessTrackingFilter implements Filter {
	@Autowired
	private CustomerService customerService;

	private static Integer totalOfUserAccessing = 0;
	private static Integer guest = 0;
	private static Integer shopMember = 0;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession(false);
		Customer authenticatedUser = getAuthenticatedUser(httpServletRequest);
		if (authenticatedUser != null) {
			shopMember++;
		} else {
			guest++;
		}
		totalOfUserAccessing++;
		System.out.println("total : " + totalOfUserAccessing);
		System.out.println("user : " + shopMember);
		System.out.println("guest : " + guest);
		chain.doFilter(request, response);

	}

	private Customer getAuthenticatedUser(HttpServletRequest request) {
		Customer customer = null;
		String email = Utility.getEmailAuthenticatedCustomer(request);
		if (email != null) {
			customer = customerService.findCustomerByEmail(email).get();
		}
		return customer;
	}

}

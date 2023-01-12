package com.cinshop;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Customer;
import com.cinshop.customer.CustomerService;

@Component
public class AccessEventListener implements ApplicationListener<HttpSessionCreatedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(AccessEventListener.class);

	@Autowired
	private CustomerService customerService;
	
	private AccessTrackStorage  storage  = AccessTrackStorage.getInstance();
	@Override
	public void onApplicationEvent(HttpSessionCreatedEvent event) {
		System.err.println("Session created : " + event.getSession().getId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String principal = (String) auth.getPrincipal();
		
		Customer customer = customerService.findCustomerByEmail(principal).get();
		if(customer != null) {
			storage.increaseMember();
		}else {
			storage.increaseGuest();
		}
		logger.info("guest : {} , member : {}, total : {}",AccessTrackStorage.getGuest(),AccessTrackStorage.getShopMember(),AccessTrackStorage.getTotalOfUserAccessing());
		
	}

}

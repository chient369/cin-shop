package com.cinshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@GetMapping("/")
	public String view() {	   
		return "index";
	}
}

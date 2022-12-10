package com.cinshop.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	
	@GetMapping("/")
	public String view() {	   
		System.out.println("testspring");
		return "index";
	}
}

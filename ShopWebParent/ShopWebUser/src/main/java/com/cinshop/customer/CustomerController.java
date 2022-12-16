package com.cinshop.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	
	@GetMapping("/login")
	public String login() {	  
		return "login";
	}
	
	@GetMapping("/testDebug")
	public String testDebug() {	  
		return "testDebug";
	}
}

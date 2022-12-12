package com.cinshop.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
	
	@GetMapping("/login")
	public String viewLoginPage() {	  
		return "login";
	}
	
	@GetMapping("/")
	public String viewSuccessPage() {	  
		return "index";
	}
}

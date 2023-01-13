package com.cinshop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinshop.common.entity.Customer;

@Controller
public class MainController {

	@GetMapping("/")
	public String home() {
		Customer customer = new Customer();
		return "index";
	}
}

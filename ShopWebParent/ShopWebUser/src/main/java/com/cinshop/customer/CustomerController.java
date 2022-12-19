package com.cinshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
	
	@GetMapping("/register")
	public String register() {	  
		return "register";
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String registerCreate(HttpServletRequest request, Customer customer) {
		
		Address address = new Address();
		Sex sex = new Sex();
		
		String gender = request.getParameter("gender");
		String postCode = request.getParameter("postCode");
		String firstAddress = request.getParameter("firstAddress");
		String lastAddress = request.getParameter("lastAddress");
		
		if (gender.equals("男")) {
			sex.setSex_id(1);
			sex.setSexName("男");
		} else {
			sex.setSex_id(2);
			sex.setSexName("女");
		}
		customer.setSex(sex);
		
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {	  
		return "login";
	}
	
	@GetMapping("/testDebug")
	public String testDebug() {	  
		return "testDebug";
	}
}

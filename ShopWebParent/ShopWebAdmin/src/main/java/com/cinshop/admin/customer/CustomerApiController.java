package com.cinshop.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

	@Autowired
	private CustomerService service;

	@GetMapping("/edit")
	public boolean setUseable(HttpServletRequest request, @RequestParam("enable") Boolean enable,
			@RequestParam("uId") Integer userId) {
		if (request.getUserPrincipal().getName() == null)
			return false;

		boolean isUpdated = service.setUseableUser(enable, userId);
		return isUpdated;
	}
}

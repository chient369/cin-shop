package com.cinshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

	@Autowired
	private OrderServiceAdmin orderService;
	
	@GetMapping("/order/{oId}")
	public String viewOrderDetail(Model model, @PathVariable Integer oId) {
		model.addAttribute("order", orderService.findOrderById(oId));
		return "orderDetail";
	}
	
}

package com.cinshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cinshop.common.OrderStatusUtility;
import com.cinshop.common.entity.Order;

@Controller
public class OrderController {

	@Autowired
	private OrderServiceAdmin orderService;
	
	@GetMapping("/order/{oId}")
	public String viewOrderDetail(Model model, @PathVariable Integer oId) {
		model.addAttribute("order", orderService.findOrderById(oId));
		return "orderDetail";
	}
	
	@GetMapping("/order/u/{oId}/{sts}")
	public String updateOrderStatus(Model model,@PathVariable("oId") Integer oId, @PathVariable("sts") Integer sts) {
		Order order = orderService.findOrderById(oId);
		order.setStatus(OrderStatusUtility.getOrderStatus(sts));
		System.out.println(order.getStatus().name());
		Order updatedOrder = orderService.updateOrder(order);
		model.addAttribute("order",updatedOrder);
		return "orderDetail";
	}
}

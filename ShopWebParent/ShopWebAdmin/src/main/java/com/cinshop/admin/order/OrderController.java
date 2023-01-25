package com.cinshop.admin.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cinshop.common.OrderStatusUtility;
import com.cinshop.common.entity.Order;

@Controller
public class OrderController {
	private final Integer ITEM_PER_PAGE = 10;

	@Autowired
	private OrderServiceAdmin orderService;
	
	@GetMapping("/order")
	public String viewFirstOrderｓPage(Model model) {
		return viewOrderｓPage(model, 1);
	}
	
	@GetMapping("/order/p/{pageNum}")
	public String viewOrderｓPage(Model model,@PathVariable Integer pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<Order> page = orderService.findAll(pageable);
		model.addAttribute("orders", page.getContent());
		model.addAttribute("orderTotalPages", page.getTotalPages());
		model.addAttribute("orderCurrentPage", page.getNumber());

		return "order";
	}
	
	@GetMapping("/order/{oId}")
	public String viewOrderDetail(Model model, @PathVariable Integer oId) {
		model.addAttribute("order", orderService.findOrderById(oId));
		return "orderDetail";
	}
	
	@GetMapping("/order/u/{oId}/{sts}")
	public String updateOrderStatus(Model model,@PathVariable("oId") Integer oId, @PathVariable("sts") Integer sts) {
		Order order = orderService.findOrderById(oId);
		order.setStatus(OrderStatusUtility.getOrderStatus(sts));
		Order updatedOrder = orderService.updateOrder(order);
		model.addAttribute("order",updatedOrder);
		return "orderDetail";
	}
}

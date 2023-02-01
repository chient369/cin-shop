package com.cinshop.admin.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cinshop.common.OrderStatusUtility;
import com.cinshop.common.entity.Order;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
	private final Integer ITEM_PER_PAGE = 10;

	@Autowired
	private OrderServiceAdmin orderService;

	@GetMapping("/order")
	public String viewFirstOrdersPage(Model model) {
		return viewOrderｓPage(model, 1);
	}

	@GetMapping("/order/p/{pageNum}")
	public String viewOrderｓPage(Model model, @PathVariable Integer pageNum) {
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
	public String updateOrderStatus(Model model, @PathVariable("oId") Integer oId, @PathVariable("sts") Integer sts) {
		Order order = orderService.findOrderById(oId);
		order.setStatus(OrderStatusUtility.getOrderStatus(sts));
		Order updatedOrder = orderService.updateOrder(order);
		model.addAttribute("order", updatedOrder);
		return "orderDetail";
	}

	@PostMapping("/order/search")
	public String searchOrder(Model model, String orderNum, String customerName) {

		Pageable pageable = PageRequest.of(0, ITEM_PER_PAGE);
		Page<Order> page = null;
		if (orderNum != null) {
			page = orderService.findByOrderNum(orderNum, pageable);
			model.addAttribute("searchBy", "orderNum");
		}
		if (customerName != null) {
			page = orderService.findByCustomerName(customerName, pageable);
			model.addAttribute("searchBy", "custName");
		}
		model.addAttribute("orders", page.getContent());
		model.addAttribute("orderTotalPages", page.getTotalPages());
		model.addAttribute("orderCurrentPage", page.getNumber());
		model.addAttribute("searchText", customerName);

		return "order";
	}

	@GetMapping("/order/search")
	public String searchOrderPage(Model model, HttpServletRequest request) {

		Map<String, String[]> param = request.getParameterMap();

		String custName = (param.get("custName") != null) ? param.get("custName")[0] : null;
		Integer pageNum = (param.get("pageNum") != null) ? Integer.parseInt(param.get("pageNum")[0]) : 0;
		String sts = (param.get("sts") != null) ? param.get("sts")[0] : null;

		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<Order> page = null;
		if (custName != null) {
			page = orderService.findByCustomerName(custName, pageable);
			model.addAttribute("searchBy", "custName");
		}
		if (sts != null) {
			page = orderService.findByOrderStatus(sts, pageable);
			model.addAttribute("searchBy", "sts");
			model.addAttribute("stsName",sts);
		}
		model.addAttribute("orders", page.getContent());
		model.addAttribute("orderTotalPages", page.getTotalPages());
		model.addAttribute("orderCurrentPage", page.getNumber());
		return "order";
	}

}

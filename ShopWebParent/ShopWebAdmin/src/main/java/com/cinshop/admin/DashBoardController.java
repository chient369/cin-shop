package com.cinshop.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cinshop.admin.customer.CustomerService;
import com.cinshop.admin.order.OrderServiceAdmin;
import com.cinshop.common.entity.Order;

@Controller
public class DashBoardController {
	private final Integer ITEM_PER_PAGE = 8;

	@Autowired
	private OrderServiceAdmin orderService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/")
	public String dashBoardFirstPage(Model model) {
		return dashBoard(1, model);
	}
	@GetMapping("/{pageNum}")
	public String dashBoard(@PathVariable Integer pageNum, Model model) {

		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<Order> page = orderService.findAll(pageable);
		model.addAttribute("orders", page.getContent());
		model.addAttribute("orderTotalPages", page.getTotalPages());
		model.addAttribute("orderCurrentPage", page.getNumber());

		model.addAttribute("deliveringOrder", orderService.countDeliveringOrder());
		model.addAttribute("orderTotal", orderService.countOrder());
		model.addAttribute("placedOrder", orderService.countPlacedOrder());
		model.addAttribute("memberTotal", customerService.countAllShopMember());
		model.addAttribute("customerTotal", customerService.countAllCustomer());
<<<<<<< HEAD
=======
		model.addAttribute("totalSale", orderService.totalSales());
>>>>>>> 2ac9847a94747f5c997472b47e38966e1ab7b69a
		return "index";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}

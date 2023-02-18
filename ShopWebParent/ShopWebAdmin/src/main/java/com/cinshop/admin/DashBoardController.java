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
import com.cinshop.admin.utility.ContactService;
import com.cinshop.common.entity.Order;

@Controller
public class DashBoardController {
	private final Integer ITEM_PER_PAGE = 8;

	@Autowired
	private OrderServiceAdmin orderService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ContactService contactService;

	@GetMapping("/")
	public String dashBoard(Model model) {
		Pageable pageable = PageRequest.of(0, ITEM_PER_PAGE);
		Page<Order> page = orderService.findByOrderStatus("PLACED",pageable);
	
		model.addAttribute("orders", page.getContent());
		model.addAttribute("contacts", contactService.findAllContact());

		model.addAttribute("deliveringOrder", orderService.countDeliveringOrder());
		model.addAttribute("orderTotal", orderService.countOrder());
		model.addAttribute("placedOrder", orderService.countPlacedOrder());
		model.addAttribute("memberTotal", customerService.countAllShopMember());
		model.addAttribute("customerTotal", customerService.countAllCustomer());
		model.addAttribute("totalSale", orderService.totalSales());
		return "index";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}

package com.cinshop.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinshop.common.entity.Customer;

@Controller
public class CustomerController {

	private final Integer ITEM_PER_PAGE = 10;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public String customersPageFirstPage(Model model) {
		return customersPage(model, 1);
	}

	@GetMapping("/customers/p/{pageNum}")
	public String customersPage(Model model, @PathVariable Integer pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<Customer> page = customerService.findAll(pageable);
		model.addAttribute("customers", page.getContent());
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		return "customers";
	}
	
	@PostMapping("/customers/search")
	public String serachByTextFirstPage(Model model,String text) {
		System.out.println(text);
		return serachByText(model, text, 1);
	}
	
	@GetMapping("/customers/search")
	public String serachByText(Model model,@RequestParam("text") String text,@RequestParam("pageNum") Integer pageNum) {
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<Customer> page = customerService.findAllByText(text, pageable);
		model.addAttribute("customers", page.getContent());
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("searchTxt",text);
		return "customers";
	}                                       
	
	@GetMapping("/customers/d/{id}")
	public String deleteUser(Model model, @PathVariable Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
}

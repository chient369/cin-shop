package com.cinshop;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.cart.CartServiceFactory;
import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.customer.CustomerService;
import com.cinshop.product.ProductDetailService;
import com.cinshop.review.ReviewService;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {
	private final Integer ITEM_PER_PAGE = 12;
	
	private Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ProductDetailService service;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartServiceFactory cartFactory;
	
	@Autowired
	private ReviewService rService;

	@GetMapping("/")
	public String viewHomePage(Model model,HttpServletRequest request) {
		Customer customer = getAuthenticatedCustomer(request);
		HttpSession session = request.getSession();
		AbstractCartService cartService = cartFactory.getCartService(customer, session);
		
		//レビュー平均値検索
		float avgVote = ((float)Math.round(rService.getAvgRanking(6) * 10))/10;
		
		if(customer != null) {
			logger.info("{}が訪問しました", customer.getFullName());
		}else {
			logger.info("GUEST{}が訪問しました", request.getSession().getId());
		}
		
		Pageable pageable = PageRequest.of(0, ITEM_PER_PAGE);
		Page<ProductDetail> page = service.finAll(pageable);
		List<Color> colors = service.findAllColors();
		List<Brand> brands = service.findAllBranchs();
		List<Category> categories = service.findAllCategories();

		model.addAttribute("products", page.getContent());
		model.addAttribute("totalPages", page.getSize());
		model.addAttribute("colors", colors);
		model.addAttribute("brands", brands);
		model.addAttribute("cats", categories);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		session.setAttribute("cart", cartService.getCartItems());
		
		//レビュー用
		model.addAttribute("avgVote", avgVote);
		return "index";
	}
	
	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		Customer customer = null;
		if (email != null)
			customer = customerService.findCustomerByEmail(email).get();
		return customer;
	}

}

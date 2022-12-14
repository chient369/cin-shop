package com.cinshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinshop.common.entity.ProductDetail;
import com.cinshop.product.ProductDetailService;

@Controller
public class ShopController {
	private final Integer ITEM_PER_PAGE = 12;
	
	@Autowired
	private ProductDetailService service;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		Pageable pageable = PageRequest.of(0, ITEM_PER_PAGE);
		Page<ProductDetail> page = service.finAll(pageable);
		
		model.addAttribute("products",page.getContent());
		model.addAttribute("totalPages",page.getSize());
		ProductDetail details = service.findById(1);
		System.out.println(details);
		
		return "index";
	}
	
}

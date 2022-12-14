package com.cinshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;

@Controller
@RequestMapping("/p")
public class ProductController {
	private final Integer ITEM_PER_PAGE = 12;

	@Autowired
	private ProductDetailService detailService;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String viewProductPage(Model model) {
		return viewPage(0, model);
	}

	@GetMapping("/page/{pnum}")
	public String viewPage(@PathVariable Integer pnum, Model model) {
		Pageable pageable = PageRequest.of(pnum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = detailService.finAll(pageable);
		model.addAttribute("products", page.getContent());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("totalPages", page.getTotalPages());
		return "product";
	}
	@GetMapping("/{pId}")
	public String viewProduct(@PathVariable Integer pId, Model model) {
		Product product = productService.findProductById(6);
		ProductDetail detail = detailService.findById(6);
		model.addAttribute("detail", detail);
		model.addAttribute("p", product);
		System.out.println(product.getProductDetail());
		return "product-detail";
	}
}

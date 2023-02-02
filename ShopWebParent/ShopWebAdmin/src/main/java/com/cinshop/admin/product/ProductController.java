package com.cinshop.admin.product;

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

import com.cinshop.common.entity.ProductDetail;

@Controller
public class ProductController {
	private final Integer ITEM_PER_PAGE = 12;

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String viewProductFirstPage(Model model) {
		return viewProductPage(model, 1);
	}
	
	@GetMapping("/product/{pId}")
	public String viewProductDetail(Model model,@PathVariable Integer pId) {
		return "product-detail";
	}

	@GetMapping("/product/p/{pageNum}")
	public String viewProductPage(Model model, @PathVariable Integer pageNum) {
		if(pageNum <1) pageNum =1;
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = productService.findAll(pageable);

		model.addAttribute("productDetails", page.getContent());
		model.addAttribute("brands", productService.findAllBrands());
		model.addAttribute("categories", productService.findAllCategories());
		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		return "products";
	}
	
	@PostMapping("/product/search")
	public String searchByTextFirstPage(Model model, String searchTxt) {
		return searchByText(model, searchTxt, 1);
		
	}
	
	@GetMapping("/product/search")
	public String searchByText(Model model,@RequestParam("s-txt") String text,@RequestParam("pageNum") Integer pageNum) {
		if(pageNum <1) pageNum =1;
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = productService.findByText(text, pageable);

		model.addAttribute("productDetails", page.getContent());
		model.addAttribute("brands", productService.findAllBrands());
		model.addAttribute("categories", productService.findAllCategories());
		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("searchTxt",text);
		return "products";
		
	}
	
	@PostMapping("/product/search/fillter")
	public String searchWithBrandAndCatFirstPage(Model model, Integer brand,Integer category) {
		return searchWithBrandAndCat(model, brand, category, 1);
		
	}
	
	@GetMapping("/product/search/fillter")
	public String searchWithBrandAndCat(Model model,@RequestParam("bId") Integer brandId,@RequestParam("catId") Integer catId,@RequestParam("pageNum") Integer pageNum) {
		if(pageNum <1) pageNum =1;
		Pageable pageable = PageRequest.of(pageNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = productService.findByBrandOrCategory(brandId, catId, pageable);

		model.addAttribute("productDetails", page.getContent());
		model.addAttribute("brands", productService.findAllBrands());
		model.addAttribute("categories", productService.findAllCategories());
		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("catId",catId);
		model.addAttribute("brandId",brandId);
		return "products";
		
	}
}

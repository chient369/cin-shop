package com.cinshop.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;

@Controller
@RequestMapping("/p")
public class ProductController {
	private final Integer ITEM_PER_PAGE = 12;

	@Autowired
	private ProductDetailService dService;

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public String viewProductPage(Model model) {

		return viewPage(1, model);
	}

	@GetMapping("/page/{pnum}")
	public String viewPage(@PathVariable Integer pnum, Model model) {
		Pageable pageable = PageRequest.of(pnum-1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.finAll(pageable);
		List<Color> colors = dService.findAllColors();
		List<Brand> brands = dService.findAllBranchs();
		List<Category> categories = dService.findAllCategories();

		model.addAttribute("products", page.getContent());
		model.addAttribute("colors", colors);
		model.addAttribute("brands", brands);
		model.addAttribute("cats", categories);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		return "product/product";
	}

	@GetMapping("/{pId}")
	public String viewProduct(@PathVariable Integer pId, Model model) {
		ProductDetail detail = dService.findById(6);

		model.addAttribute("p", detail.getProducts());
		model.addAttribute("detail", detail);
		model.addAttribute("colors", findExistColors(detail.getProducts()));

		return "product/product-detail";
	}
	@GetMapping("/search/cat/{catId}")
	public String searchByCatFirstPage(@PathVariable Integer catId, Model model) {
		return searchByCategory(catId, 1, model);

	}
	@GetMapping("/search/cat/{catId}/{pNum}")
	public String searchByCategory(@PathVariable Integer catId, @PathVariable Integer pNum, Model model) {
		if(pNum == null) pNum=1;
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByCategory(catId, pageable);
		List<Color> colors = dService.findAllColors();
		List<Brand> brands = dService.findAllBranchs();
		List<Category> categories = dService.findAllCategories();

		model.addAttribute("products", page.getContent());
		model.addAttribute("colors", colors);
		model.addAttribute("brands", brands);
		model.addAttribute("cats", categories);
		model.addAttribute("totalPages", page.getTotalPages());
		System.out.println("total "+page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("catId", catId);
		return "product/product-by-category";
	}

	private List<Color> findExistColors(List<Product> products) {
		List<Color> colors = new ArrayList<>();
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			colors.add(it.next().getColor());
		}
		return colors;
	}
}

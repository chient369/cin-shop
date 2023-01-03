package com.cinshop.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Size;

@RestController
@RequestMapping("/api/p")
public class ProductApiController {

	@Autowired
	private ProductDetailService service;

	@GetMapping("/gs")
	public List<Size> findExistSizesByColor(@RequestParam("cId") Integer colorId,
			@RequestParam("dId") Integer productId) {
		List<Size> sizes = new ArrayList<>();
		ProductDetail detail = service.findById(productId);
		Set<Product> products = detail.getProducts();

		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			if (product.getColor().getId().equals(colorId)) {
				sizes.add(product.getSize());
			}
		}
		return sizes;
	}

	@GetMapping("/gb")
	public List<Brand> getBrands() {
		List<Brand> brands = service.findAllBranchs();
		return brands;
	}
}

package com.cinshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Size;

@RestController
@RequestMapping("/api/pd")
public class ProductApiController {

	@Autowired
	private ProductDetailService service;

	@GetMapping("/cat")
	public ResponseEntity<List<Category>> getCategoryJson() {

		List<Category> categories = service.findAllCategories();
		return ResponseEntity.ok().body(categories);

	}

	@GetMapping("/b")
	public ResponseEntity<List<Brand>> getBrandJson() {

		List<Brand> brands = service.findAllBranchs();
		return ResponseEntity.ok().body(brands);

	}

	@GetMapping("/colors")
	public ResponseEntity<List<Color>> getColorsJson() {

		List<Color> colors= service.findAllColors();
		return ResponseEntity.ok().body(colors);

	}

	@GetMapping("/sizes")
	public ResponseEntity<List<Size>> getInitData() {

		List<Size> sizes = service.findAllSizes();
		return ResponseEntity.ok().body(sizes);

	}

}

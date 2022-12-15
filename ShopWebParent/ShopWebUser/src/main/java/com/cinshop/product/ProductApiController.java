package com.cinshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pd")
public class ProductApiController {

	@Autowired
	private ProductDetailService service;

	
}

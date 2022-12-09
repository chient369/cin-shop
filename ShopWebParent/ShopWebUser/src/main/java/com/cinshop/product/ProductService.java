package com.cinshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Product;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepository repository;
	
	public Product findProductById(Integer id) {
		return repository.findById(id).get(0);
	}
	
	
}

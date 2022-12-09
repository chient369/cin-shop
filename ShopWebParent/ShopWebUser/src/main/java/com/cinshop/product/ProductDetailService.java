package com.cinshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.ProductDetail;

@Service
public class ProductDetailService {
	
	
	@Autowired
	private ProductDetailRepository detailRepository;
	
	public ProductDetail findById(Integer id) {
		return detailRepository.findById(id).get(0);
	}
	
	public Page<ProductDetail> finAll(Pageable pageable){
		return detailRepository.findAll(pageable);
		
	}

	public Page<ProductDetail> findByWord(String word, Pageable pageable){
		return detailRepository.findByWord(word, pageable);
	}

	public Page<ProductDetail> findByBrand(Brand brand, Pageable pageable){
		return detailRepository.findByBrand(brand, pageable);
	}

	public Page<ProductDetail> findByCategory(Category category, Pageable pageable){
		return detailRepository.findByCategory(category, pageable);
	}
	
	
	public Page<ProductDetail> findByPrice(Integer pFrom, Integer pTo, Pageable pageable){
		return detailRepository.findByPrice(pFrom, pTo, pageable);
	}
}

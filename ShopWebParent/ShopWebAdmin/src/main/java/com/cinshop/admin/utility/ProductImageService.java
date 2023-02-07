package com.cinshop.admin.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.ProductImage;

import jakarta.transaction.Transactional;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageRepository repository;
	
	public void saveImage(Integer detailId, String imageName) {
		repository.save(new ProductImage(imageName, new ProductDetail(detailId)));
	}	
	@Transactional
	public void delete(String imageName) {
		repository.deleteByName(imageName);
	}
}

package com.cinshop.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	List<Product> findById(Integer id);
	
	@Query("select p from Product p where p.productDetail.id = ?1 and p.color.id = ?2 and p.size.id = ?3")
	Product findByDetailIdAndColorIdAndSizeId(Integer detailId, Integer colorId,Integer sizeId);
	
	

}

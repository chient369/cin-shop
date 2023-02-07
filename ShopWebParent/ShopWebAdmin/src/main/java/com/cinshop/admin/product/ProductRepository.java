package com.cinshop.admin.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.ProductDetail;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetail, Integer> {

	@Query("select p from ProductDetail p where concat(p.name,p.description) like %?1%")
	Page<ProductDetail> findByText(String text, Pageable pageable);
	
	@Query("select p from ProductDetail p where p.brand.id =?1 or p.category.id =?2")
	Page<ProductDetail> findByBrandOrCategory(Integer brandId,Integer catId,Pageable pageable);
}

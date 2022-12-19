package com.cinshop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetail, Integer> {

	List<ProductDetail> findById(Integer id);

	Page<ProductDetail> findAll(Pageable pageable);

	@Query("select p from ProductDetail p where p.name like %?1%")
	Page<ProductDetail> findByText(String word, Pageable pageable);

	Page<ProductDetail> findByBrand(Brand brand, Pageable pageable);

	Page<ProductDetail> findByCategory(Category category, Pageable pageable);

	@Query("select p from ProductDetail p WHERE p.price between ?1 and ?2")
	Page<ProductDetail> findByPrice(Integer pFrom, Integer pTo, Pageable pageale);
	
	@Query("select distinct d from ProductDetail d  "
			+ "inner join d.products p "
			+ "where p.color.id = ?1")
	Page<ProductDetail> findByColor(Integer colorId, Pageable pageable); 
	
//	@Query("select distinct d from ProductDetail d  "
//			+ "inner join d.reviews r "
//			+ "order by r.vote asc")
//	Page<ProductDetail> findAllWithRankASC(Pageable pageable); 
}

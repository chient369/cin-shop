package com.cinshop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.ProductDetail;

@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetail, Integer> {
	@Query("select p from ProductDetail p where p.id=?1 and p.enable = 1")
	List<ProductDetail> findById(Integer id);

	@Query("select p from ProductDetail p where p.enable = 1")
	Page<ProductDetail> findAll(Pageable pageable);

	@Query("select p from ProductDetail p where p.name like %?1% and p.enable = 1")
	Page<ProductDetail> findByText(String word, Pageable pageable);

	@Query("select p from ProductDetail p where p.brand.id = ?1 and p.enable = 1")
	Page<ProductDetail> findByBrand(Integer brandId, Pageable pageable);

	@Query("select p from ProductDetail p where p.category.id = ?1 and p.enable = 1")
	Page<ProductDetail> findByCategory(Integer category, Pageable pageable);

	@Query("select p from ProductDetail p WHERE p.enable = 1 and p.price between ?1 and ?2")
	Page<ProductDetail> findByPrice(Integer pFrom, Integer pTo, Pageable pageale);

	@Query("select distinct d from ProductDetail d  " + "inner join d.products p "
			+ "where p.color.id = ?1 and d.enable =1")
	Page<ProductDetail> findByColor(Integer colorId, Pageable pageable);

//	@Query("select distinct d from ProductDetail d  "
//			+ "inner join d.reviews r "
//			+ "order by r.vote asc")
//	Page<ProductDetail> findAllWithRankASC(Pageable pageable); 
}

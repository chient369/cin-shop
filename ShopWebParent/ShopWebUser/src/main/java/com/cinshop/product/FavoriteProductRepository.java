package com.cinshop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavoriteProduct;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Integer> {

	public List<FavoriteProduct> findByCustomer(Customer customer);
	
	public Page<FavoriteProduct> findByCustomer(Customer customer, Pageable pageable);
	
	@Query("select f from FavoriteProduct f where f.customer.id = ?1 and f.productDetail.id = ?2")
	public FavoriteProduct findByCustIdAndDetailId(Integer custId, Integer dId);
	
	@Query("select f from FavoriteProduct f where f.customer.id = ?1")
	public List<FavoriteProduct> findByCustId(Integer custId);
}

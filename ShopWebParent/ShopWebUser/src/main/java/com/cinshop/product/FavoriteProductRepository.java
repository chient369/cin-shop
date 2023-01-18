package com.cinshop.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavoriteProduct;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Integer> {

	public List<FavoriteProduct> findByCustomer(Customer customer);

}

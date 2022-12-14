package com.cinshop.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavouriteProduct;

@Repository
public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Integer> {

	public List<FavouriteProduct> findByCustomer(Customer customer);

}

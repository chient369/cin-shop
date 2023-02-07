package com.cinshop.admin.utility;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.ProductImage;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImage, Integer>  {

	@Modifying
	@Query("delete from ProductImage i where i.name = ?1")
	void deleteByName(String name);
}

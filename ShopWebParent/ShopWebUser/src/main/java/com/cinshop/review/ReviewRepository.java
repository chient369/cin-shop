package com.cinshop.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByProductDetail(ProductDetail productDetail);
}

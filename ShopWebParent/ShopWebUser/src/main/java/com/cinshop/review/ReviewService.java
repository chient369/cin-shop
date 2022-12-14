package com.cinshop.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Review;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository repository;

	public List<Review> findReviewsByProductDetailId(Integer detailId) {
		return repository.findByProductDetail(new ProductDetail(detailId));

	}
	@Transactional
	public Review addReview(Review review) {
		return repository.save(review);
	}
	

}

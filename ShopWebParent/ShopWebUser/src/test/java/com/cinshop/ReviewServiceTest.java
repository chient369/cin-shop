package com.cinshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Review;
import com.cinshop.review.ReviewService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class ReviewServiceTest {

	@Autowired
	private ReviewService service;
	
	@Test
	public void addReiewTest() {
		Review review = new Review();
		review.setCustomer(new Customer(1));
		review.setProductDetail(new ProductDetail(1));
		review.setContent("すごくいい商品だった");
		review.setVote(5);
		
		Review savedReview = service.addReview(review);
		assertThat(savedReview).isNotNull();
	}
	
	@Test
	public void findAllReviewsByProductDetail() {
		List<Review> reviews = service.findReviewsByProductDetailId(1);
		assertThat(reviews.size()).isGreaterThan(0);
	}
}

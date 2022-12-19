package com.cinshop.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.ProductDetail;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductDetailServiceTest {

	@Autowired
	private ProductDetailService service;
	
	@Test
	public void findProductDetailByIdTest() {
		ProductDetail product = service.findById(1);
		System.out.println(product.getImages());
		assertThat(product).isNotNull();
	}

	@Test
	public void findAllProductDetailTest() {
		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.finAll(pageable);
		assertThat(details.getSize()).isGreaterThan(0);
	}
	
	@Test
	public void findProductDetailByBrandTest() {

		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.findByBrand(1, pageable);
		assertThat(details.getSize()).isGreaterThan(0);
	}
	@Test
	public void findProductDetailByCategoryTest() {

		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.findByCategory(1, pageable);
		assertThat(details.getSize()).isGreaterThan(0);
	}
	@Test
	public void findProductDetailByColorTest() {

		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.findByColor(1, pageable);
		System.out.println(details.getTotalElements());
		assertThat(details.getSize() == 1);
	}
	
	@Test
	public void findProductByPriceTest() {
		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.findByPrice(500, 3000, pageable);
		assertThat(details.getSize()).isGreaterThan(0);
	}
	
	@Test
	public void findProductByWordTest() {
		Pageable pageable =PageRequest.of(1, 10);
		Page<ProductDetail> details = service.findByText("nik", pageable);
		assertThat(details.getSize()).isGreaterThan(0);
	}
	
//	@Test
//	public void findAllWithRankASCTest() {
//		Pageable pageable =PageRequest.of(1, 10);
//		Page<ProductDetail> details = service.findAllWithRankASC(pageable);
//		assertThat(details.getSize()).isGreaterThan(0);
//	}
}

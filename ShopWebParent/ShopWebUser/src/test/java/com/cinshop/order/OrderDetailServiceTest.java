package com.cinshop.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class OrderDetailServiceTest {

	@Autowired
	private OrderDetailService service;
	
	@Test
	public void findOrderDetailsByProductIdTest() {
		List<OrderDetail> orderDetails = service.findOrderDetailsByProductId(1);
		assertThat(orderDetails.size()).isGreaterThan(0);
		
	}
	
	
}

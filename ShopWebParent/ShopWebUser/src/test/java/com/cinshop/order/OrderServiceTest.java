package com.cinshop.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.Tax;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback()
public class OrderServiceTest {

	@Autowired
	private OrderService service;
	
	@Autowired
	private OrderUtility utility;

	@Test
	public void saveOrderTest() {
		Order order = new Order();
		OrderDetail o1 = new OrderDetail();
		o1.setProduct(new Product(1));
		o1.setProduct(new Product(2));
		OrderDetail o2 = new OrderDetail();
		o2.setProduct(new Product(1));
		o2.setProduct(new Product(2));
		order.addOrderDetail(o1);
		order.addOrderDetail(o2);
		order.setCustomer(new Customer(1));

		Order savedOrder = service.saveOrder(order);
		assertThat(savedOrder).isNotNull();
	}

	//@Test
//	public void findOrderByCustomerId() {
//		List<Order> orders = service.findOrderByCustomerId(1);
//		orders.forEach(order -> System.out.println(order.getId()));
//		assertThat(orders.size()).isGreaterThan(0);
//	}

	@Test
	public void findOrderByIdTest() {
		Order order = service.findOrderById(1);
		System.err.println(order);
		order.getOrderDetails().forEach(od -> System.out.println("Order detail id : " + od.getId()));
		assertThat(order).isNotNull();
	}
	@Test
	public void updateOrderTest() {
		Order order = service.findOrderById(2);
		order.setStatus(OrderStatus.PAID);
		Order savedOrder = service.saveOrder(order);
		System.err.println(savedOrder.getStatus());
		assertThat(savedOrder).isNotNull();
	}
	
	@Test
	public void getCurrentTaxTest() {
		Tax tax  = utility.getCurrentTax();
		System.err.println("Current Tax :" + tax.getTax());
		assertThat(tax).isNotNull();
	}

}

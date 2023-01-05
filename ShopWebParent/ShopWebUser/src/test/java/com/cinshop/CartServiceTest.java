//package com.cinshop;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import com.cinshop.cart.CartServiceImpl;
//import com.cinshop.common.entity.CartItem;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(value = false)
//public class CartServiceTest {
//
//	@Autowired
//	private CartServiceImpl service;
//
//	@Test
//	public void addItemTest() {
//		service.saveCartItem(1, 2, 10);
//		service.saveCartItem(1, 1, 99);
//		service.saveCartItem(2, 1, 11);
//	}
//
//	@Test
//	public void findCartItemsByCustomerTest() {
//		List<CartItem> cartItems = service.findCartItemsByCustomerId(1);
//		cartItems.forEach(crt -> System.err.println(crt));
//		assertThat(cartItems.size()).isGreaterThan(0);
//	}
//
//	@Test
//	public void updateItemTest() {
//		service.saveCartItem(2, 1, 22);
//		System.out.println("updated quantity : 22" );
//	}
//	
//	@Test
//	public void removeItemTest() {
//		service.removeItem(1, 1);
//	}
//
//	@Test
//	public void deleteCartTest() {
//		service.deleteCartByCustomerid(1);
//	}
//
//}

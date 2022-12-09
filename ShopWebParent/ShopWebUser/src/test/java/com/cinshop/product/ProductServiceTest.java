//package com.cinshop.product;
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
//import com.cinshop.common.entity.Product;
//import com.cinshop.product.ProductService;
//
//@DataJpaTest
//@Rollback(value = false)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class ProductServiceTest {
//
//	@Autowired
//	private ProductService service;
//	
//	//@Test
////	public void findAllTest() {
////		List<Product> products = service.();
////		assertThat(products.size()).isGreaterThan(0);
////	}
//}

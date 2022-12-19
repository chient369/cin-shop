package com.cinshop.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Product;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductServiceTest {

	@Autowired
	private ProductService service;

//	@Test
//	public void findProductById() {
//		Product product = service.findProductById(1);
//		List<Color> colors = product.getColors();
//		colors.forEach(c -> System.out.println(c.getName()));
//		assertThat(product).isNotNull();
//	}
}

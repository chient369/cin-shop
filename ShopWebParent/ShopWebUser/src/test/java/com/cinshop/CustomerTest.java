package com.cinshop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Customer;
import com.cinshop.customer.CustomerService;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerTest {
	
	@Autowired
	private CustomerService service;
	
	@Test
	public void saveCustomerTest() {
		Customer customer = new Customer();
		customer.setName("Chien");
		Customer savedCust = service.saveCustomer(customer);
		
		assertThat(savedCust).isNotNull();
	}

}

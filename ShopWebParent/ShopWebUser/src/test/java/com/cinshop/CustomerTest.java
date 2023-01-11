package com.cinshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Sex;
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
		Address address = new Address();
		Sex sex = new Sex();
		
		//性別		

		//insert into sex values(1, "男");insert into sex values(2, "女");insert into sex values(0, "未登録");	登録してから実行
		sex.setSex_id(1);
		sex.setSexName("男");
		
		//顧客登録
		customer.setEmail("値を変える@gmail.com");
		customer.setFirstName("山田");
		customer.setLastName("太郎");
		customer.setPassword("$2a$08$KAfQk5Ix8xCnIpYsAUT2yeCdKBe3Cnw9vU5VVEIQDR3x3n0F7Pntq");	//encode前の値は"admin"
		customer.setRole("ROLE_USER");
		customer.setSex(sex);
		customer.setEnable((byte)1);
		customer.setImage("img/xxx.jpg");
		customer.setPhoneNumber("xxx-xxxx-xxxx");
		customer.setPoint(100);
		
		Customer savedCust = service.save(customer);
		assertThat(savedCust).isNotNull();
		
		//住所登録
		//address.setCustomer(customer);
		address.setPostCode("xxx-xxxxx");
		address.setFirstAddress("hoge県");
		address.setLastAddress("hoge市hogex-x-x");

		Address savedAddr = service.saveAddress(address);
		assertThat(savedAddr).isNotNull();
	}
	
	@Test
	public void findCustomer() {
		Optional<Customer> customer = service.findCustomerByEmail("値を変える@gmail.com");
		assertThat(customer).isNotNull();
	}
	
	@Test
	public void loginCustomerTest() {
	}
}

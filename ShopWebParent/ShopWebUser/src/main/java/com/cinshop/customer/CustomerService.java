package com.cinshop.customer;

import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;

@Service
public class CustomerService{
	
	private Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AddressRepository AddrRepo;
	
	public Customer saveCustomer(Customer customer) {
		return custRepo.save(customer);
	}
	
	public Address saveAddress(Address address) {
		return AddrRepo.save(address);
	}
	
	public Optional<Customer> findCustomerByEmail(String email) {
		return custRepo.findCustomerByEmail(email);
	}
	public Customer saveGuest(Customer guest) {
		Long checkExistAcc = custRepo.countByEmail(guest.getEmail());
		if (checkExistAcc > 0) {
			Customer existAcc = custRepo.findCustomerByEmail(guest.getEmail()).get();
			// DBに情報を更新するために、フォームから受け取ったインスタンスをDBにある顧客IDを設定する
			guest.setId(existAcc.getId());

		}
		guest.setRole("ROLE_GUEST");
		// ゲストに仮パスワードを設定する
		guest.setPassword(Calendar.getInstance().getTimeInMillis() + "");
		return custRepo.save(guest);
	}
} 


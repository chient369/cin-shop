package com.cinshop.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public long countAllCustomer() {
		return repository.count();
	}
	public long countAllShopMember() {
		return repository.countShopMember();
	}
	
	public long countGuest() {
		return repository.countGuest();
	}
	
	public Page<Customer> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
}

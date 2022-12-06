package com.cinshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
}

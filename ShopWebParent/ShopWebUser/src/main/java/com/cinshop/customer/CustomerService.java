package com.cinshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private AddressRepository Addrrepo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Address saveAddress(Address address) {
		return Addrrepo.save(address);
	}
} 

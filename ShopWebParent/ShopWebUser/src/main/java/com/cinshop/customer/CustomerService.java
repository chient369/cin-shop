package com.cinshop.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;

@Service
public class CustomerService{
	
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
} 

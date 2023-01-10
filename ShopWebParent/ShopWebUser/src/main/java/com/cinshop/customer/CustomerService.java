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
	
	public Customer save(Customer cust) {
		return custRepo.save(cust);
	}
	
	public void update(Integer custId, String newPassword) {
		custRepo.update(custId, newPassword);
	}
	
	public Optional<Customer> findCustomerByEmail(String email) {
		return custRepo.findCustomerByEmail(email);
	}
	
	public Address saveAddress(Address address) {
		return AddrRepo.save(address);
	}
	
	public Optional<Address> findAddressById(Integer custId) {
		return AddrRepo.findAddressById(custId);
	}
} 

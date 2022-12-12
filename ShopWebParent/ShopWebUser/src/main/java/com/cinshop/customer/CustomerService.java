package com.cinshop.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Customer;

@Service
public class CustomerService implements UserDetailsService{
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private AddressRepository AddrRepo;
	
	@Autowired
	private LoginUserRepository LoginRepo;
	
	public Customer saveCustomer(Customer customer) {
		return custRepo.save(customer);
	}
	
	public Address saveAddress(Address address) {
		return AddrRepo.save(address);
	}
	
	public Customer findCustomerByEmail(String email) {
		return custRepo.findCustomerByEmail(email);
	}
	
	//spring security service
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<LoginUser> userOp = LoginRepo.findCustomerByEmail(email);
		return userOp.map(user -> new LoginUserDetails(user))
		.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
} 

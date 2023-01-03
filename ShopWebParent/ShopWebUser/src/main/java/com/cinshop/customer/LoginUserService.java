package com.cinshop.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.cinshop.common.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService{
	
	@Autowired
	public CustomerRepository custRepo;
	
	private List<Customer> roleCust;
	private List<String> roleStr = new ArrayList<String>();
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Customer> userOp = custRepo.findCustomerByEmail(email);
		if (!userOp.isEmpty()) {
			roleStr.add("ROLE_USER");
			return new LoginUserDetails(userOp, roleStr);
		} else {
			throw new UsernameNotFoundException("not found");
		}
	}
}
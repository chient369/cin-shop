package com.cinshop.customer;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService{
	
	public final LoginUserRepository loginRepo;
	
	public LoginUserService(LoginUserRepository loginRepo) {
		this.loginRepo = loginRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<LoginUser> userOp = loginRepo.findCustomerByEmail(email);
		return userOp.map(user -> new LoginUserDetails(user))
		.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}

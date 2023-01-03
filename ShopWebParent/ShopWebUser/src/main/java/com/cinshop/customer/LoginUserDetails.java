package com.cinshop.customer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cinshop.common.entity.Customer;

public class LoginUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private Optional<Customer> customer;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(Optional<Customer> customer, List<String> roleList) {
        this.customer = customer;
        this.authorities = roleList
           		.stream()
                .map(role -> new SimpleGrantedAuthority(role))
               	.toList();
    }

    public Optional<Customer> getCustomer() {
        return this.customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.customer.get().getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.get().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getFullName() {
    	return customer.get().getFullName();
    }
}

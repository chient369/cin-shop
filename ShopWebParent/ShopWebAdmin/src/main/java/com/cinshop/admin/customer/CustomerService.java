package com.cinshop.admin.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void delete(Integer id) {
		repository.deleteById(id);
		
	}
	public Page<Customer> findAllByText(String txt,Pageable pageable){
		return repository.findAllByText(txt, pageable);
	}
	@Transactional
	public boolean setUseableUser(Boolean enable,Integer userId) {
		boolean isupdated = false;
		try {
			repository.setUseableUser(enable, userId);
			isupdated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isupdated;
	}
	
}

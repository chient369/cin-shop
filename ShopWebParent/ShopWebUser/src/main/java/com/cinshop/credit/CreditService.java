package com.cinshop.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;

@Service
public class CreditService{
	
	@Autowired
	private CreditRepository creditRepo;
	
	
	public void saveCreditDetail(Credit credit) {
		creditRepo.save(credit);
		
	}
	public Credit findCreditByCustomer(Integer id) {
		return creditRepo.findByCustomer(new Customer(id));
	}
	

}

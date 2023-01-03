package com.cinshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.common.entity.Tax;
import com.cinshop.credit.CreditRepository;
import com.cinshop.utility.PaymentMethodRepository;
import com.cinshop.utility.TaxRepository;

@Component
public class OrderUtility {

	@Autowired
	private  TaxRepository taxRepository;
	
	@Autowired
	private  PaymentMethodRepository paymentRepository;
	
	@Autowired
	private  CreditRepository creditRepository;
	

	public  Tax getCurrentTax() {		
		return taxRepository.findCurrentTax();
	}
	public  List<PaymentMethod> findAllPaymentMethods() {
		
		return (List<PaymentMethod>) paymentRepository.findAll();
		
	}
	public void saveCreditDetail(Credit credit) {
		creditRepository.save(credit);
	}
	
}

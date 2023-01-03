package com.cinshop.credit;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;

@Repository
public interface CreditRepository extends CrudRepository<Credit, Integer> {

	Credit findByCustomer(Customer customer);

}

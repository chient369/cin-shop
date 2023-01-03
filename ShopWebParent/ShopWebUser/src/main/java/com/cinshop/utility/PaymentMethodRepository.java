package com.cinshop.utility;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.PaymentMethod;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {

}

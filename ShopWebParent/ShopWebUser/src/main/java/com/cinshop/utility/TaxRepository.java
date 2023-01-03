package com.cinshop.utility;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Tax;

@Repository
public interface TaxRepository extends CrudRepository<Tax, Integer> {

	@Query("select t from Tax t order by t.effectiveDate limit 1")
	public Tax findCurrentTax();
}

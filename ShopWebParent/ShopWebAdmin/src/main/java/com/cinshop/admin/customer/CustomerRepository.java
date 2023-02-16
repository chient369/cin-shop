package com.cinshop.admin.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select count(c) from Customer c where c.role = 'ROLE_USER'")
	public long countShopMember();

	@Query("select count(c) from Customer c where c.role = 'ROLE_GUEST'")
	public long countGuest();

	@Modifying
	@Query("delete from Customer c where c.id=?1")
	public void deleteById(Integer id);

	@Query("select c from Customer c where concat(c.firstName,c.lastName,c.email) like %?1%")
	public Page<Customer> findAllByText(String txt, Pageable pageable);
	
	@Modifying
	@Query("update Customer c set c.enable = ?1 where c.id = ?2")
	public void setUseableUser(Boolean enable,Integer userId);
}

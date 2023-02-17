package com.cinshop.admin.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	@Modifying
	@Query("update Contact c set c.isReplied = true where c.id = ?1")
	public void updateStatus(Integer id);
}

package com.cinshop.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}

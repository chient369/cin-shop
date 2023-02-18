package com.cinshop.admin.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Contact;

@Service
public class ContactService {

	@Autowired
	private MailSenderHelper mailSender;

	@Autowired
	private ContactRepository repository;

	public List<Contact> findAllContact() {
		return repository.findAll();
	}

	public void deleteContact(Integer id) {
		repository.deleteById(id);
	}

	public Contact findById(Integer id) {
		return repository.findById(id).get();
	}

	public void updateStatus(Integer id) {
		repository.updateStatus(id);
	}

	public boolean reply(String email, String content) {
		boolean isSended = false;
		try {
			mailSender.sendEmail(email, content);
			isSended = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSended;
	}
}

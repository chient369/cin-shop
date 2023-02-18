package com.cinshop.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.admin.utility.ContactService;
import com.cinshop.common.entity.Contact;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/contact/{id}")
	public Contact getContact(@PathVariable Integer id) {
		return contactService.findById(id);
	}

	@PostMapping("/contact/send")
	public boolean getContact(String email, String content) {

		return contactService.reply(email, content);
	}
}

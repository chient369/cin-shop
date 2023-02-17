package com.cinshop.contact;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Contact;
import com.cinshop.notification.NotifyDTO;
import com.cinshop.notification.NotifyDTO.NotifyType;
import com.cinshop.notification.UserShopNotify;

@Service
public class ContactService {
	private static Logger logger = LoggerFactory.getLogger(ContactService.class);
	@Autowired
	private ContactRepository repository;
	
	@Autowired
	private UserShopNotify notify;
	
	public void saveContact(String email, String content) {
		Date receivedDate = new Date();
		Contact saved = repository.save(new Contact(email,content,receivedDate));
		if(saved != null) {
			NotifyDTO dto = new NotifyDTO();
			dto.setType(NotifyType.CONTACT);
			dto.getInfo().put("contact", saved);
			dto.getInfo().put("msg", "新規メッセージが受信しました");
			
			notify.sendNotify(dto);
			logger.info("{}からメッセージが受信しました",saved.getEmail());
			return;
		}
		logger.error("Failed in SaveContact");
	}
}

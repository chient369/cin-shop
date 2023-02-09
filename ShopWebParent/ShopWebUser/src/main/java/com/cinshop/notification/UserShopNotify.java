package com.cinshop.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserShopNotify {
	@Autowired
	private SimpMessagingTemplate template;

	public void sendNotify(@Payload NotifyDTO notifyDTO) {
		template.convertAndSend("/topic/admin", notifyDTO);
	}

}

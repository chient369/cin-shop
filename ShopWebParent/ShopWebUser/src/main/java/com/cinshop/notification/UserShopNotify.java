package com.cinshop.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableAsync
public class UserShopNotify {
	@Autowired
	private SimpMessagingTemplate template;

	@Async
	public void sendNotify(@Payload NotifyDTO notifyDTO) {
		template.convertAndSend("/topic/admin", notifyDTO);
	}

}

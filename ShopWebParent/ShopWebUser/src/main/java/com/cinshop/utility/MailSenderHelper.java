package com.cinshop.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
@Configuration
@EnableAsync
public class MailSenderHelper {

	private Logger logger = LoggerFactory.getLogger(MailSenderHelper.class);
	@Value("${spring.mail.username}")
	private String FROM;

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SpringTemplateEngine template;

	@Async
	public void orderedNotify(String toEmail, Order order) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

			System.out.println(order);
			Context context = new Context();
			context.setVariable("order", order);
			context.setVariable("details", order.getOrderDetails());
			context.setVariable("cust", order.getCustomer());
			Integer productCostTotal = 0;
			for (OrderDetail detail : order.getOrderDetails()) {
				productCostTotal+= detail.getSubTotal();
			}
			context.setVariable("pCostTotal", productCostTotal);

			String htmlTemplate = template.process("mail/order-mail", context);

			helper.setFrom(FROM);
			helper.setTo(order.getCustomer().getEmail());
			helper.setText(htmlTemplate, true);
			helper.setSubject("[Cin-Shopより、ご注文の内容を確認]");

			mailSender.send(message);
			logger.info("Order Notify mail send successfull");
		} catch (MessagingException e) {
			logger.error("Order Notify of email send with error :" + e.getMessage());
		}
	}

}

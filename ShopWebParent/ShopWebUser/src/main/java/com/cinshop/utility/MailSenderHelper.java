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
	
	@Value("${cinshop.requestURI}")
	private String baseUrl;
	
	@Value("${cinshop.shopAdmin.path}")
	private String adminShopPath;
	
	@Value("${cinshop.admin.email}")
	private String adminEmail;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine template;

	@Async
	public void orderedNotify(String toEmail, Order order) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

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
System.err.println(order.getPaymentMethod().getNameJa());
System.err.println(order.getPaymentMethod().getId());
			mailSender.send(message);
			sendtoAdminEmail();
			logger.info("Order Notify mail send successfull");
		} catch (MessagingException e) {
			logger.error("Order Notify of email send with error :" + e.getMessage());
		}
	}
	@Async
	public void sendEmail(String toEmail, String auth, Integer custId) {
		MimeMessage message = mailSender.createMimeMessage();
		String resetUrl = baseUrl + "/rstp?custId=" + custId + "&auth=" + auth;
		String text = "<html>"
					+ "<head></head>"
					+ "<body>"
					+ "<p>認証URLをクリックして、パスワードの変更を完了させてください。</p>"
					+ "<p><a href='" + resetUrl + "'></a>" + resetUrl + "</p>"
					+ "</body>"
					+ "</html>";
	    try {
	      //送信情報設定
	      MimeMessageHelper helper = new MimeMessageHelper(message, true);
	      helper.setFrom(FROM);
	      helper.setTo(toEmail);
	      //helper.setCc("xxxxx@xxx.xx");
	      //helper.setBcc("xxxxx@xxx.xx");
	      helper.setSubject("【重要】パスワード再設定のお知らせ");
	      helper.setText(text, true);
	      //メール送信
	      mailSender.send(message);
	    } catch(MessagingException e) {
	      e.printStackTrace();
	    }
	}
	@Async
	public void sendtoAdminEmail() {
		MimeMessage message = mailSender.createMimeMessage();
		String adminShop = adminShopPath +"/order";
		String text = "<html>"
					+ "<head></head>"
					+ "<body>"
					+ "<p>新規注文がありました。確認してください</p>"
					+ "<p><a href='" + adminShop + "'></a>" + adminShop + "</p>"
					+ "</body>"
					+ "</html>";
	    try {
	      //送信情報設定
	      MimeMessageHelper helper = new MimeMessageHelper(message, true);
	      helper.setFrom(FROM);
	      helper.setTo(adminEmail);
	      //helper.setCc("xxxxx@xxx.xx");
	      //helper.setBcc("xxxxx@xxx.xx");
	      helper.setSubject("【重要】新規注文のお知らせ");
	      helper.setText(text, true);
	      //メール送信
	      mailSender.send(message);
	    } catch(MessagingException e) {
	      e.printStackTrace();
	    }
	}

}

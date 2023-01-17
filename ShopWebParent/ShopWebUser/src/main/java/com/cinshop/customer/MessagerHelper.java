package com.cinshop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MessagerHelper {
	
	@Value("${spring.mail.username}")
	private String shopEmail;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String auth) {
		MimeMessage message = mailSender.createMimeMessage();
		String text = "<html>"
					+ "<head></head>"
					+ "<body>"
					+ "<p>認証URLをクリックして、パスワードの変更を完了させてください。</p>"
					+ "<p><a href='" + auth + "'></a>" + auth + "</p>"
					+ "</body>"
					+ "</html>";
	    try {
	      //送信情報設定
	      MimeMessageHelper helper = new MimeMessageHelper(message, true);
	      helper.setFrom(shopEmail);
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
}

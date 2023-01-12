package com.cinshop.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.Product;

@Component
@Configuration
@EnableAsync
public class MailSenderHelper {

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void orderedNotify(String toEmail, Order order) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(toEmail);
		message.setSubject("[CIN-SHOPより]ご注文を受け付けました");
		message.setText(generateOrderedNotify(order));
		mailSender.send(message);

	}

	private static String generateOrderedNotify(Order order) {
		List<OrderDetail> details = order.getOrderDetails();
		Customer customer = order.getCustomer();
		File file = new File("src/main/resources/mail/order-mail.txt");
		StringBuilder stringBuilder = new StringBuilder();
		Integer subTotal = 0;
		try {
			BufferedReader b_reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			while ((line = b_reader.readLine()) != null) {
				if (line.contains("#custName"))
					line = line.replace("#custName", customer.getFullName());
				if (line.contains("#orderDate"))
					line = line.replace("#orderDate", order.getOrderDateString());
				if (line.contains("#address"))
					line = line.replace("#address", customer.getAddress().getFullAddress());
				if (line.contains("#phoneNumber"))
					line = line.replace("#phoneNumber", customer.getPhoneNumber());
				if (line.contains("#total"))
					line = line.replace("#total",  order.getTotal().toString());
				if (line.contains("#orderNum"))
					line = line.replace("#orderNum", order.getOrderNum());
				if (line.contains("#shipCost"))
					line = line.replace("#shipCost", "500");
//				if (line.contains("#paymentMethod"))
//					line = line.replace("#paymentMethod", order.getPaymentMethod().getNameJa());

				if (line.contains("#detail")) {
					line = line.replace("#detail", "");
					for (int i = 0; i < details.size(); i++) {
						Product product = details.get(i).getProduct();
						subTotal += product.getPrice() * details.get(i).getQuantity();
						stringBuilder.append(i + 1 + ".[" + product.getProductDetail().getBrand().getName() + "] ")
								.append(product.getProductDetail().getName() + " " + product.getSize().getValue()
										+ "cm")
								.append("\n").append("値段: ¥" + product.getPrice())
								.append("\t" + "数量 : " + details.get(i).getQuantity()).append("\n")
								.append("合計: ¥" + product.getPrice() * details.get(i).getQuantity()).append("\n\n");
					}
				}
				if (line.contains("#subTotal")) {
					line = line.replace("#subTotal",  subTotal.toString());
				} else {
					stringBuilder.append(line).append("\n");
				}
			}
			b_reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

}

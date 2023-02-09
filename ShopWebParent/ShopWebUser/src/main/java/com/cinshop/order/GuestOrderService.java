package com.cinshop.order;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.cart.GuestCartService;
import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.customer.CustomerService;

@Service
public class GuestOrderService extends AbstractOrderService {
	private final String CUSTOMER = "customer";
	private Logger logger = LoggerFactory.getLogger(GuestOrderService.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public Order saveOrder(Map<String, Object> orderInfo) {
		GuestCartService cartService = (GuestCartService)super.cartService;
		Customer customer = (Customer) cartService.getSession().getAttribute(CUSTOMER);
		
		PaymentMethod paymentMethod = (PaymentMethod) orderInfo.get(PAYMENT_METHOD);		
		Order order = new Order();
		List<OrderDetail> details = saveOrderDetail(order);
		Iterator<OrderDetail> it = details.iterator();

		// 注文詳細データを注文に保存する
		while (it.hasNext()) {
			OrderDetail detail = it.next();
			order.addOrderDetail(detail);
		}
		//注文情報設定
		order.setOrderTime(new Date());
		order.setPaymentMethod(paymentMethod);
		order.setStatus(OrderStatus.PLACED);
		order.setShippingCost(getShippingCost());
		order.setTax(utility.getCurrentTax());
		order.setTotal(getTotal());

		// 仮に0割引を設定
		order.setDiscountPercent(0);
		try {
			Customer savedCustomer = customerService.saveGuest(customer);
			logger.info("{}の情報を保存しました", customer.getFullName());
			order.setCustomer(savedCustomer);
			Order savedOrder = orderService.saveOrder(order);
			
			logger.info("注文{}の詳細を格納した", order.getOrderNum());			
			
			mailSenderHelper.orderedNotify(customer.getEmail(), savedOrder);
			
			sendNotidy(savedOrder);
			cartService.deleteCart();
		} catch (Exception e) {
			logger.error("{}の注文詳細を保存失敗", customer.getFullName());
			e.printStackTrace();
		}
		return order;
	}

}

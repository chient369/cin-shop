package com.cinshop.order;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.cart.CustomerCartService;
import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.PaymentMethod;

@Service
public class CustomerOrderService extends AbstractOrderService {
	private static final Integer CREDIT_METHOD = 4;
	private Logger logger = LoggerFactory.getLogger(CustomerOrderService.class);

	public Page<Order> findOrderByCustomerId(Integer id,Pageable pageable) {
		return orderService.findOrderByCustomerId(id,pageable);
	}
	public Order findOrderById(Integer id) {
		return orderService.findOrderById(id);
	}
	@Override
	public Order saveOrder(Map<String, Object> orderInfo) {	
		CustomerCartService cartService = (CustomerCartService)super.cartService;
		Customer customer = cartService.getCustomer();
		
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
		order.setCustomer(customer);
		order.setOrderTime(new Date());
		order.setPaymentMethod(paymentMethod);
		order.setStatus(OrderStatus.PLACED);
		order.setShippingCost(getShippingCost());
		order.setTax(utility.getCurrentTax());
		order.setTotal(getTotal());

		// 仮に0割引を設定
		order.setDiscountPercent(0);
		try {
			Order savedOrder = orderService.saveOrder(order);
			logger.info("注文{}の詳細を格納した", order.getOrderNum());
			logger.info("{}の注文詳細を保存完了", customer.getFullName());
			
			if (paymentMethod.getId() == CREDIT_METHOD) {
				Credit credit = (Credit) orderInfo.get(CREDIT_DETAIL);
				credit.setCustomer(customer);
				saveCreditDetails(credit);
			}
			
			mailSenderHelper.orderedNotify(customer.getEmail(), savedOrder);
			cartService.deleteCart();
		} catch (Exception e) {
			logger.error("{}の注文詳細を保存失敗", customer.getFullName());
			e.printStackTrace();
		}
		return order;

	}
	public void saveCreditDetails(Credit credit) {
		utility.saveCreditDetail(credit);
	}
	

}

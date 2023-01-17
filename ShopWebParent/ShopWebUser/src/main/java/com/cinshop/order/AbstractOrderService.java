package com.cinshop.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.common.OrderStatus;
import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.OrderDetail;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.common.entity.Tax;

@Service
public abstract class AbstractOrderService {
	private Logger logger = LoggerFactory.getLogger(AbstractOrderService.class);

	private AbstractCartService cartService;

	@Autowired
	protected OrderService orderService;

	@Autowired
	private OrderUtility utility;

	private Order order;
	private List<OrderDetail> details = new ArrayList<>();

	public Order saveOrder(Customer customer, PaymentMethod paymentMethod) {
		details = saveOrderDetail();
		Iterator<OrderDetail> it = details.iterator();

		// 各注文詳細データを注文に保存する
		while (it.hasNext()) {
			OrderDetail detail = it.next();
			order.addOrderDetail(detail);
		}
		order.setCustomer(customer);
		order.setOrderTime(new Date());
		// クレジットカードで支払い場合、顧客のカード情報を格納必要がある
		order.setPaymentMethod(paymentMethod);
		order.setStatus(OrderStatus.PLACED);
		order.setShippingCost(getShippingCost());
		order.setTax(utility.getCurrentTax());
		order.setTotal(getTotal());

		// 仮に1割引を設定
		order.setDiscountPercent(0);
		try {
			orderService.saveOrder(order);
		
			logger.info("注文{}の詳細を格納した", this.order.getOrderNum());
			logger.info("{}の注文詳細を保存完了", customer.getFullName());
			cartService.deleteCart();
		} catch (Exception e) {
			logger.error("{}の注文詳細を保存失敗", customer.getFullName());
			e.printStackTrace();
		}
		return this.order;
	

	}

	private List<OrderDetail> saveOrderDetail() {
		List<CartItem> cartItems = cartService.findCartItems();
		Iterator<CartItem> it = cartItems.iterator();
		while (it.hasNext()) {
			CartItem item = (CartItem) it.next();
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(item.getProduct());
			detail.setQuantity(item.getQuantity());

			Integer subTotal = item.getQuantity() * item.getProduct().getPrice();
			detail.setSubTotal(subTotal);
			details.add(detail);
		}
		return details;
	}

	public Integer getTotalIgnoretax() {
		List<CartItem> cartItems = cartService.findCartItems();
		Integer subTotal = 0;
		for (CartItem item : cartItems) {
			subTotal += item.getQuantity() * item.getProduct().getPrice();
		}
		return subTotal;
	}

	public Integer getTotalWithTax() {
		Tax tax = utility.getCurrentTax();
		return getTotalIgnoretax() +( getTotalIgnoretax() * tax.getTax() / 100);
	}

	public Integer getTotal() {
		return getShippingCost() + getTotalWithTax();
	}

	public Integer getShippingCost() {
		// 仮に配送料は５００円を設定
		// データベースから問合せる可能
		return 500;
	}

	public Tax getCurrentTax() {
		return utility.getCurrentTax();
	}

	public List<PaymentMethod> findAllPaymentMethods() {

		return utility.findAllPaymentMethods();

	}

	public void saveCreditDetails(Credit credit) {
		utility.saveCreditDetail(credit);
		logger.info("顧客ID{}のクレジットカード情報を格納した.", credit.getCustomer().getId());
	}

	/* setter - getter */

	public AbstractCartService getCartService() {
		return cartService;
	}

	public void setCartService(AbstractCartService cartService) {
		this.order = new Order();
		this.cartService = cartService;
	}

}

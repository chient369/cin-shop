package com.cinshop.order;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.cart.CartServiceFactory;
import com.cinshop.cart.CustomerCartService;
import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.customer.CustomerService;
import com.cinshop.utility.MailSenderHelper;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	private static final Integer CREDIT_METHOD = 4;

	@Autowired
	private OrderServiceFactory orderServiceFactory;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartServiceFactory cartServiceFactory;

	@Autowired
	private MailSenderHelper mailSenderHelper;

	@GetMapping("/order")
	public String checkout(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		if (customer != null) {
			AbstractCartService cartService = cartServiceFactory.getCartService(customer, null);
			AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);
			model.addAttribute("total", orderService.getTotalWithTax());
			model.addAttribute("shipCost", orderService.getShippingCost());
			model.addAttribute("cartItems", cartService.findCartItems());
			model.addAttribute("cust", customer);
			model.addAttribute("paymentMethods", orderService.findAllPaymentMethods());
			return "/order-confirm";
		}
		// ゲストの場合、個人情報を入力するフォームに移行
		model.addAttribute("customer", new Customer());
		return "guest-form-order";

	}

	@PostMapping("/order/g")
	public String checkoutForGuest(@ModelAttribute Customer guest, Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();

			AbstractCartService cartService = cartServiceFactory.getCartService(null, session);
			AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);

			// ゲストが入力した情報をDBに保存する又は更新する
			Customer savedGuest = customerService.saveGuest(guest);

			model.addAttribute("total", orderService.getTotal());
			model.addAttribute("shipCost", orderService.getShippingCost());
			model.addAttribute("cartItems", cartService.findCartItems());
			model.addAttribute("cust", savedGuest);
			model.addAttribute("paymentMethods", orderService.findAllPaymentMethods());

			// 注文する時に使う情報
			session.setAttribute("customer", savedGuest);

			return "/order-confirm";
		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
	}

	@GetMapping("/order/placed")
	public String placeOrder(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		HttpSession session = request.getSession();

		AbstractCartService cartService = cartServiceFactory.getCartService(customer, session);
		System.err.println(cartService.toString());
		
		AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);

		// 支払方法を決定処理

		Order order;
		String email;
		PaymentMethod paymentMethod = getPaymentMethod(request);
		//会員で場合、クレジットカードの情報を格納する

		if (cartService instanceof CustomerCartService) {
			order = orderService.saveOrder(customer, paymentMethod);
			if (paymentMethod.getId() == CREDIT_METHOD) {
				Credit credit = getCreditDetail(request);
				credit.setCustomer(customer);
				orderService.saveCreditDetails(credit);
			}
			email = customer.getEmail();
		} else {
			Customer guest = (Customer) session.getAttribute("customer");
			order = orderService.saveOrder(guest, paymentMethod);
			email = guest.getEmail();
		}
		mailSenderHelper.orderedNotify(email, order);

		session.removeAttribute("cart");
		return "order-success";
	}

	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		Customer customer = null;
		if (email != null)
			customer = customerService.findCustomerByEmail(email).get();
		return customer;
	}

	private PaymentMethod getPaymentMethod(HttpServletRequest request) {
		Map<String, String[]> methods = request.getParameterMap();
		Integer methodId = Integer.parseInt(methods.get("paymentMethod")[0]);
		return new PaymentMethod(methodId);

	}

	private Credit getCreditDetail(HttpServletRequest request) {
		Map<String, String[]> methods = request.getParameterMap();
		Credit credit = new Credit();
		String crdNum = methods.get("crd-num")[0];
		String crdOwner = methods.get("crd-name")[0];
		Integer MM = Integer.parseInt(methods.get("crd-month")[0]);
		Integer YYYY = Integer.parseInt(methods.get("crd-year")[0]);
		String securCode = methods.get("secur-code")[0];
		boolean checkValidValues = methods.values().stream().allMatch((param) -> param.length > 0);
		if (checkValidValues) {
			credit.setCardNum(crdNum);
			credit.setCustomer(null);
			credit.setOwnerName(crdOwner);
			credit.setSecurCode(securCode);
			Calendar date = Calendar.getInstance();
			date.set(YYYY, MM, 1);
			credit.setExpiry(date.getTime());
		}
		return credit;
	}

}

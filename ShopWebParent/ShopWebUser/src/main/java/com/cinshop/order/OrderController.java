package com.cinshop.order;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.cart.CartServiceFactory;
import com.cinshop.cart.CustomerCartService;
import com.cinshop.common.entity.Credit;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Order;
import com.cinshop.common.entity.PaymentMethod;
import com.cinshop.customer.CustomerService;
import com.cinshop.exception.NotLoginException;
import com.cinshop.utility.MailSenderHelper;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	private static final Integer CREDIT_METHOD = 4;
	private final Integer ITEM_PER_PAGE = 3;

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
		HttpSession session = request.getSession();
		AbstractCartService cartService = cartServiceFactory.getCartService(customer, session);
		AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);
		if (customer != null) {
			model.addAttribute("total", orderService.getTotalWithTax());
			model.addAttribute("shipCost", orderService.getShippingCost());
			model.addAttribute("cartItems", cartService.findCartItems());
			model.addAttribute("cust", customer);
			model.addAttribute("paymentMethods", orderService.findAllPaymentMethods());
			return "/order-confirm";
		}
		// ?????????????????????????????????????????????????????????????????????
		if (cartService.getCartItems() == null) {
			return "error/403";
		}
		model.addAttribute("customer", new Customer());
		return "guest-form-order";

	}

	@PostMapping("/order/g")
	public String checkoutForGuest(@ModelAttribute Customer guest, Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();

			AbstractCartService cartService = cartServiceFactory.getCartService(null, session);
			AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);

			// ?????????????????????????????????DB?????????????????????????????????
			Customer savedGuest = customerService.saveGuest(guest);

			model.addAttribute("total", orderService.getTotal());
			model.addAttribute("shipCost", orderService.getShippingCost());
			model.addAttribute("cartItems", cartService.findCartItems());
			model.addAttribute("cust", savedGuest);
			model.addAttribute("paymentMethods", orderService.findAllPaymentMethods());

			// ??????????????????????????????
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

		AbstractOrderService orderService = orderServiceFactory.getOrderService(cartService);

		// ???????????????????????????

		Order order;
		String email;
		PaymentMethod paymentMethod = getPaymentMethod(request);
		// ??????????????????????????????????????????????????????????????????

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

	@GetMapping("/order/history")
	public String orderHistoryFirstpage(Model model, HttpServletRequest request) throws NotLoginException {
		return orderHistory(model, request, 1);
	}

	@GetMapping("/order/history/{pNum}")
	public String orderHistory(Model model, HttpServletRequest request, @PathVariable Integer pNum)
			throws NotLoginException {
		Customer customer = getAuthenticatedCustomer(request);
		if (customer == null) {
			throw new NotLoginException("Deny Access!!");
		}
		CustomerOrderService orderService = orderServiceFactory.getCustomerService();
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<Order> orders = orderService.findOrderByCustomerId(customer.getId(), pageable);
		model.addAttribute("orders", orders.getContent());
		model.addAttribute("totalPages", orders.getTotalPages());
		model.addAttribute("currentPage", orders.getNumber());
		return "order-history";
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

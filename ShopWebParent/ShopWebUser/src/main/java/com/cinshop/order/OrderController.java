package com.cinshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cinshop.cart.CartService;
import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Customer;
import com.cinshop.customer.CustomerService;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderUtility utility;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartService cartService;

	@GetMapping("/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		List<CartItem> cartItems;
		if (email != null) {
			Customer customer = customerService.findCustomerByEmail(email).get();
			cartItems = cartService.findCartItemsByCustomerId(customer.getId());
			model.addAttribute("cust", customer);
			model.addAttribute("cartItems", cartItems);
			return "check-out";
		}
		
		return "guest-form-order";

	}

	@PostMapping("/checkout/g")
	public String checkoutFOrGuest(Model model,HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		List<CartItem> cartItems;
		if (email != null) {
			Customer customer = customerService.findCustomerByEmail(email).get();
			cartItems = cartService.findCartItemsByCustomerId(customer.getId());
			model.addAttribute("cust", customer);
			model.addAttribute("cartItems", cartItems);
			return "check-out";
		}
		try {
			HttpSession session = request.getSession();
			List<CartItem> carts = (List<CartItem>) session.getAttribute("cart");
			Integer tax = utility.getCurrentTax().getTax();

			Integer subTotal = 0;
			for (CartItem item : carts) {
				subTotal += (item.getQuantity() * item.getProduct().getProductDetail().getPrice());
			}

			Integer taxIncluded = subTotal + subTotal * tax / 100;
			model.addAttribute("cartItems", carts);
			model.addAttribute("total", taxIncluded);

		} catch (Exception e) {
			return "404";
		}
		return "check-out";
	}
}

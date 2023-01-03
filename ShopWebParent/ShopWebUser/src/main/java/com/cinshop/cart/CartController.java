package com.cinshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.Product;
import com.cinshop.customer.CustomerService;
import com.cinshop.product.ProductService;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CustomerService service;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartServiceFactory factory;

	@GetMapping("")
	public String viewCart(Model model, HttpServletRequest request) {

		Customer customer = getAuthenticatedCustomer(request);
		HttpSession session = request.getSession();

		AbstractCartService cartService = factory.getCartService(customer, session);
		List<CartItem> cartItems = cartService.findCartItems();

		/* session期限内使えるようにする */
		session.setAttribute("cart", cartItems);
		model.addAttribute("cartItems", cartItems);
		return "shopping-cart";
	}

	@PostMapping("/a")
	public String addCartItem(Integer detailId, Integer color, Integer size, Integer quantity,
			HttpServletRequest request) {
		Product product = productService.findByDetailIdAndColorIdAndSizeId(detailId, color, size);
		if (product == null)
			// 商品が存在しない場合、エラーページに移行
			return "404";

		try {
			Customer customer = getAuthenticatedCustomer(request);
			HttpSession session = request.getSession();

			AbstractCartService cartService = factory.getCartService(customer, session);
			List<CartItem> cartItems = cartService.addCartItem(product, quantity);

			session.setAttribute("cart", cartItems);

		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
		return "redirect:/cart";
	}

	@PostMapping("/update")
	public String updateCartItem(HttpServletRequest request) {

		try {
			Customer customer = getAuthenticatedCustomer(request);
			HttpSession session = request.getSession();

			AbstractCartService cartService = factory.getCartService(customer, session);
			List<CartItem> cartItems = cartService.updateCartItem(request);
			
			session.setAttribute("cart", cartItems);

		} catch (Exception e) {
			e.printStackTrace();
			return "404";
		}
		return "redirect:/cart";
	}

	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		Customer customer = null;
		if (email != null)
			customer = service.findCustomerByEmail(email).get();
		return customer;
	}
}

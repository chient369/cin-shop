package com.cinshop.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private CartService cartService;

	@GetMapping("")
	public String viewCart(Model model, HttpServletRequest request) {
		List<CartItem> cartItems = null;
		try {
			String email = Utility.getEmailAuthenticatedCustomer(request);
			if (email != null) {
				Customer customer = service.findCustomerByEmail(email).get();
				cartItems = cartService.findCartItemsByCustomerId(customer.getId());
			} else {
				HttpSession session = request.getSession();
				cartItems = (List<CartItem>) session.getAttribute("cart");
			}
		} catch (Exception e) {

			return "404";
		}
		model.addAttribute("cartItems", cartItems);
		return "shopping-cart";
	}

	@PostMapping("/a")
	public String addCartItem(Integer detailId, Integer color, Integer size, Integer quantity,
			HttpServletRequest request) {
		Product product = productService.findByDetailIdAndColorIdAndSizeId(detailId, color, size);
		if (product == null)
			return "404";

		try {
			// 会員の場合
			String email = Utility.getEmailAuthenticatedCustomer(request);
			if (email != null) {
				Customer customer = service.findCustomerByEmail(email).get();
				cartService.saveCartItem(customer.getId(), product.getId(), quantity);
			} else {
				// ゲストの場合
				HttpSession session = request.getSession();
				CartItem cartItem = new CartItem(null, product, quantity);
				if (session.getAttribute("cart") == null) {
					List<CartItem> cartItems = new ArrayList<>();
					cartItems.add(cartItem);
					session.setAttribute("cart", cartItems);
				} else {
					List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
					if (cartItems.contains(cartItem)) {
						Integer existItemOfIndex = cartItems.indexOf(cartItem);
						cartItem.setQuantity(cartItems.get(existItemOfIndex).getQuantity() + quantity);
						cartItems.remove(cartItem);
					}
					cartItems.add(cartItem);
					session.setAttribute("cart", cartItems);
				}
			}
		} catch (Exception e) {
			return "404";
		}
		return "redirect:/cart";
	}

	@PostMapping("/update")
	public String updateCartItem(HttpServletRequest request) {

		Map<String, String[]> param = request.getParameterMap();
		String[] productId = param.get("productId");
		String[] quantity = param.get("quantity");
		try {
			// 会員の場合
			String email = Utility.getEmailAuthenticatedCustomer(request);
			if (email != null) {
				Customer customer = service.findCustomerByEmail(email).get();
				for (int i = 0; i < productId.length; i++) {
					cartService.saveCartItem(1, Integer.parseInt(productId[i]), Integer.parseInt(quantity[i]));
				}
			} else {
				// ゲストの場合
				HttpSession session = request.getSession();
				List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
				for (int i = 0; i < cartItems.size(); i++) {
					cartItems.get(i).setQuantity(Integer.parseInt(quantity[i]));
				}
				cartItems.removeIf(item -> item.getQuantity() ==0);
				session.setAttribute("cart", cartItems);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return "404";
		}
		return "redirect:/cart";
	}
}

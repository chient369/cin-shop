package com.cinshop;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cinshop.cart.AbstractCartService;
import com.cinshop.cart.CartServiceFactory;
import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.contact.ContactService;
import com.cinshop.customer.CustomerService;
import com.cinshop.product.FavoriteProductService;
import com.cinshop.product.ProductDetailService;
import com.cinshop.utility.Utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {
	private final Integer ITEM_PER_PAGE = 12;

	private Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ProductDetailService service;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartServiceFactory cartFactory;

	@Autowired
	private FavoriteProductService fService;

	@Autowired
	private ContactService contactService;

	@GetMapping("/")
	public String viewHomePage(Model model, HttpServletRequest request,
			@CookieValue(name = "key", required = false, defaultValue = "no data") String v) {
		Customer customer = getAuthenticatedCustomer(request);
		HttpSession session = request.getSession();
		AbstractCartService cartService = cartFactory.getCartService(customer, session);

		if (customer != null) {
			logger.info("{}が訪問しました", customer.getFullName());
		} else {
			logger.info("GUEST{}が訪問しました", request.getSession().getId());
		}

		Pageable pageable = PageRequest.of(0, ITEM_PER_PAGE);
		Page<ProductDetail> page = service.finAll(pageable);
		List<Color> colors = service.findAllColors();
		List<Brand> brands = service.findAllBranchs();
		List<Category> categories = service.findAllCategories();

		// お気に入り登録しているか判定
		//お気に入り集計
		avgVoteCalc(page);

		List<FavoriteProduct> favoriteProduct = new ArrayList<FavoriteProduct>();
		if (customer != null) {
			favoriteProduct = fService.findByCustomer(customer.getId());
			boolean detailIdMatch = false;
			for (int i = 0; i < page.getContent().size(); i++) {
				for (int j = 0; j < favoriteProduct.size(); j++) {
					if (page.getContent().get(i).getId() == favoriteProduct.get(j).getProductDetail().getId()) {
						detailIdMatch = true;
					}
				}
				page.getContent().get(i).setFavoriteChecked(detailIdMatch);
				detailIdMatch = false;
			}
		} else {
			if (!v.equals("no data")) {
				String[] values = v.split(",");

				boolean detailIdMatch = false;
				for (int i = 0; i < page.getContent().size(); i++) {
					for (int j = 0; j < values.length; j++) {
						if (page.getContent().get(i).getId().toString().equals(values[j])) {
							detailIdMatch = true;
						}
					}
					page.getContent().get(i).setFavoriteChecked(detailIdMatch);
					detailIdMatch = false;
				}
			}
		}

		model.addAttribute("products", page.getContent());
		model.addAttribute("totalPages", page.getSize());
		model.addAttribute("colors", colors);
		model.addAttribute("brands", brands);
		model.addAttribute("cats", categories);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", page.getNumber());
		session.setAttribute("cart", cartService.getCartItems());

		return "index";
	}

	@GetMapping("/about")
	public String viewAbout() {
		return "about";
	}

	@GetMapping("/contact")
	public String viewContact() {
		return "contact";
	}

	@PostMapping("/contact")
	public String receivedContact(Model model, String email,String content) {
		contactService.saveContact(email, content);
		model.addAttribute("msg", "メッセージが送信しました。返事をお待ちください");
		return "redirect:/contact";
	}

	private Customer getAuthenticatedCustomer(HttpServletRequest request) {
		String email = Utility.getEmailAuthenticatedCustomer(request);
		Customer customer = null;
		if (email != null)
			customer = customerService.findCustomerByEmail(email).get();
		return customer;
	}
	
	private void avgVoteCalc(Page<ProductDetail> page) {
		//ここからレビュー、平均集計
		float avgVote = 0.0F;
		float totalVote = 0.0F;
		for (int i = 0; i < page.getContent().size(); i++) {
			for (int j = 0; j < page.getContent().get(i).getReviews().size(); j++) {
				totalVote += page.getContent().get(i).getReviews().get(j).getVote().floatValue();
			}
			avgVote = totalVote / page.getContent().get(i).getReviews().size();
			avgVote = ((float)Math.round(avgVote * 10))/10;
			page.getContent().get(i).setAvgVote(avgVote);
			totalVote = 0.0F;
			avgVote = 0.0F;
		}
	}
}

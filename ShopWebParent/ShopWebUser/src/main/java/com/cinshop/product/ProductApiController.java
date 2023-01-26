package com.cinshop.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.common.entity.Address;
import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Size;
import com.cinshop.customer.LoginUserDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/p")
public class ProductApiController {

	@Autowired
	private ProductDetailService service;
	
	@Autowired
	private FavoriteProductService fService;

	@GetMapping("/gs")
	public List<Size> findExistSizesByColor(@RequestParam("cId") Integer colorId,
			@RequestParam("dId") Integer productId) {
		List<Size> sizes = new ArrayList<>();
		ProductDetail detail = service.findById(productId);
		Set<Product> products = detail.getProducts();

		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			if (product.getColor().getId().equals(colorId)) {
				sizes.add(product.getSize());
			}
		}
		return sizes;
	}

	@GetMapping("/gb")
	public List<Brand> getBrands() {
		List<Brand> brands = service.findAllBranchs();
		return brands;
	}
	
	@PostMapping("/fav")
	public FavoriteProduct addFavouriteProduct(@RequestParam("dId") Integer dId, @AuthenticationPrincipal LoginUserDetails userDetails,
			@CookieValue(name= "key", required = false, defaultValue = "no data") String v, HttpServletResponse response){
		FavoriteProduct favoriteAdd = new FavoriteProduct();
		//会員
		if (userDetails != null) { 
			favoriteAdd = fService.addFavProduct(userDetails.getCustomer().get().getId(), dId);
		} else {
			//ゲスト
			if (v.equals("no data")) {
				Cookie cookie = new Cookie("key", dId.toString() + '^');
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
			} else {
				List<String> values = Arrays.asList(v.split("^"));
				System.out.println(values.size());
				Cookie cookie = new Cookie("key", (values.get(0) + dId + '^'));
				cookie.setAttribute("key", (values.get(0) + dId + '^'));
				//cookie.setHttpOnly(true);
				response.addCookie(cookie);
			}
		}
		return favoriteAdd;
	}
	
	@PostMapping("/fav/remove")
	public void removeFavouriteProduct(@RequestParam("dId") Integer dId, @AuthenticationPrincipal LoginUserDetails userDetails,
			@CookieValue(name= "key", required = false, defaultValue = "no data") String v, HttpServletResponse response) {
		//会員
		if (userDetails != null) { 
			fService.removeFavProduct(userDetails.getCustomer().get().getId(), dId);
		//ゲスト	
		} else {
			List<String> values = Arrays.asList(v.split("^"));
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i) == dId.toString()) {
					values.remove(i);
				}
			}
			Cookie cookie = new Cookie("key", values.toString());
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}
	}
	
	//http://localhost:8085/cinshop/api/p/fav/remove
	@PostMapping("/fav/remove/a")
	private String remove(HttpServletRequest request, HttpServletResponse response) {
	    Cookie[] cookies = request.getCookies();
	    for (Cookie cookie : cookies) {
	        if ("id".equals(cookie.getName())) {
	            cookie.setMaxAge(0);
	            cookie.setPath("/");
	            response.addCookie(cookie);
	        }
	    }
	    return "redirect:/p";
	}
}

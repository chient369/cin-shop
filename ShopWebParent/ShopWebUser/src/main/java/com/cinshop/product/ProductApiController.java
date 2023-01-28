package com.cinshop.product;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Size;
import com.cinshop.customer.LoginUserDetails;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	public void addFavouriteProduct(@RequestParam("dId") Integer dId, @AuthenticationPrincipal LoginUserDetails userDetails,
			@CookieValue(name= "key", required = false, defaultValue = "no data") String v, HttpServletResponse response, HttpServletRequest request) throws IOException{
		//会員
		if (userDetails != null) { 
			fService.addFavProduct(userDetails.getCustomer().get().getId(), dId);
		} else {
			//ゲスト
			if (v.equals("no data")) {
				String encCookie = URLEncoder.encode(dId.toString() + ",", "UTF-8");
				Cookie cookie = new Cookie("key", encCookie);
				cookie.setPath("/cinshop");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				response.addCookie(cookie);
			} else {
				String encCookie = URLEncoder.encode(v + dId.toString() + ",", "UTF-8");
				Cookie cookie = new Cookie("key", encCookie);
				cookie.setPath("/cinshop");
				cookie.setMaxAge(365 * 24 * 60 * 60);
				response.addCookie(cookie);
			}
		}
	}
	
	@PostMapping("/fav/remove")
	public void removeFavouriteProduct(@RequestParam("dId") Integer dId, @AuthenticationPrincipal LoginUserDetails userDetails,
			@CookieValue(name= "key", required = false, defaultValue = "no data") String v, HttpServletResponse response) throws IOException {
		//会員
		if (userDetails != null) { 
			fService.removeFavProduct(userDetails.getCustomer().get().getId(), dId);
		//ゲスト	
		} else {
			List<String> list = new ArrayList<String>(Arrays.asList(v.split(",")));
			String combCookie = "";
			System.out.println(list);
			
	        Iterator<String> it = list.iterator();
	        while (it.hasNext()) {
	            String item = it.next();
	            if (item.equals(dId.toString())) {
	                it.remove();
	            }
	        }
			for (String s : list) {combCookie += URLEncoder.encode(s + ',', "UTF-8");}
			
			Cookie cookie = new Cookie("key", null);
			cookie.setValue(combCookie);
			cookie.setPath("/cinshop");
			cookie.setMaxAge(365 * 24 * 60 * 60);
			response.addCookie(cookie);
		}
	}
	
	@PostMapping("/favProductLength")
	public int findFavoriteProduct(@CookieValue(name= "key", required = false, defaultValue = "no data") String v, @AuthenticationPrincipal LoginUserDetails userDetails) {
		int size = 0;
		//会員
		if (userDetails != null) { 
			List<FavoriteProduct> list = fService.findAllByCustomerId(userDetails.getCustomer().get().getId());
			size = list.size();
		} else {
			//ゲスト
			String[] list = v.split(",");
			if (!v.equals("no data"))
				size = list.length;
		}
		return size;
	}
}

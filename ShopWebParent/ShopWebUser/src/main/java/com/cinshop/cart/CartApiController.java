package com.cinshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinshop.dto.CartItemDTO;
/*
 * APIにアクセスする前に、Fillterクラスでログインしたかどうかをチェックする
 * */
@RestController
@RequestMapping("/api/cart")
public class CartApiController {

	@Autowired
	private CartService service;

	@PostMapping
	public ResponseEntity<List<CartItemDTO>> addItem(@RequestBody CartItemDTO dto) {
		service.saveCartItem(dto.getCustomerId(), dto.getProductId(), dto.getQuantity());
		
		List<CartItemDTO> responseData = service.findCartItemsByCustomerId(dto.getCustomerId());
		
		return ResponseEntity.ok(responseData);

	}

	@GetMapping("/{custId}")
	public List<CartItemDTO> getCartItems(@PathVariable Integer custId) {
		//与えたIDのユーザーがログインしたかどうかをチェック
		List<CartItemDTO> cartItems = service.findCartItemsByCustomerId(custId);
		cartItems.forEach(ci -> System.out.println(ci));
		return cartItems;
	}

	@DeleteMapping
	public ResponseEntity<List<CartItemDTO>> removeItem(@RequestBody CartItemDTO dto) {
		service.removeItem(dto.getCustomerId(), dto.getProductId());
		List<CartItemDTO> responeData = service.findCartItemsByCustomerId(dto.getCustomerId());
		return ResponseEntity.ok().body(responeData);

	}
	
	
}

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

import com.cinshop.common.entity.CartItem;
import com.cinshop.dto.CartItemDTO;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

	@Autowired
	private CartService service;

	@PostMapping
	public ResponseEntity<CartItemDTO> addItem(@RequestBody CartItemDTO cartItemDTO) {
		service.saveCartItem(cartItemDTO.getCustId(), cartItemDTO.getProductId(), cartItemDTO.getQuantity());
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/{custId}")
	public ResponseEntity getCartItems(@PathVariable Integer custId) {
		List<CartItem> cartItems = service.findCartItemsByCustomerId(custId);
		cartItems.forEach(ci -> System.out.println(ci));
		return ResponseEntity.ok().body(cartItems);
	}

	@DeleteMapping
	public ResponseEntity removeItem(@RequestBody CartItemDTO dto) {
		service.removeItem(dto.getCustId(), dto.getProductId());
		return ResponseEntity.ok().build();

	}
}

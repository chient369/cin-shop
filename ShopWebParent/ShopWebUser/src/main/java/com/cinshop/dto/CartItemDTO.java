package com.cinshop.dto;

import com.cinshop.common.entity.CartItem;
import com.cinshop.common.entity.Product;

public class CartItemDTO {
	private Integer customerId;
	private Integer productId;
	private String productImage;
	private String productName;
	private Integer quantity;

	public CartItemDTO() {
		
	}

	public void convertTo(CartItem cartItem) {
		this.productId= cartItem.getProduct().getId();
		this.productImage = cartItem.getProduct().getProductDetail().getMainImage();
		this.productName = cartItem.getProduct().getProductDetail().getName();
		this.quantity = cartItem.getQuantity();
	}
	
	
	public CartItemDTO(Integer customerId, Integer productId) {
		super();
		this.customerId = customerId;
		this.productId = productId;
	}

	public CartItemDTO(Integer customerId, Integer productId, String productImage, String productName,
			Integer quantity) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.productImage = productImage;
		this.productName = productName;
		this.quantity = quantity;
	}



	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
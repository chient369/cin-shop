package com.cinshop.dto;

import com.cinshop.common.entity.CartItem;

public class CartItemDTO {
	private Integer customerId;
	private Integer productId;
	private String image;
	private String productName;
	private Integer quantity;
	private Integer price;

	public CartItemDTO() {

	}
	public CartItemDTO(CartItem cartItem) {
		this.productId = cartItem.getProduct().getId();
		this.image = cartItem.getProduct().getProductDetail().getMainImage();
		this.productName = cartItem.getProduct().getProductDetail().getName();
		this.quantity = cartItem.getQuantity();
		this.price = cartItem.getProduct().getProductDetail().getPrice();
	}

	public void convertToDto(CartItem cartItem) {
		this.productId = cartItem.getProduct().getId();
		this.image = cartItem.getProduct().getProductDetail().getMainImage();
		this.productName = cartItem.getProduct().getProductDetail().getName();
		this.quantity = cartItem.getQuantity();
		this.price = cartItem.getProduct().getProductDetail().getPrice();
	}

	public CartItemDTO(Integer customerId, Integer productId) {
		super();
		this.customerId = customerId;
		this.productId = productId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItemDTO [customerId=" + customerId + ", productId=" + productId + ", image=" + image
				+ ", productName=" + productName + ", quantity=" + quantity + ", price=" + price + "]";
	}
	

}
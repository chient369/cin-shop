package com.cinshop.dto;

public class CartItemDTO {
	private Integer custId;
	private Integer productId;
	private Integer quantity;

	public CartItemDTO() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CartItemDTO(Integer custId, Integer productId, Integer quantity) {
		super();
		this.custId = custId;
		this.productId = productId;
		this.quantity = quantity;
	}
	

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

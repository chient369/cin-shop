package com.cinshop.dto;

public class FavouriteProductDTO {

	private Integer id;
	private Integer customer_id;
	private Integer product_id;

	public FavouriteProductDTO() {

	}

	public FavouriteProductDTO(Integer id, Integer customer_id, Integer product_id) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.product_id = product_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "FavouriteProductDTO [id=" + id + ", customer_id=" + customer_id + ", product_id=" + product_id + "]";
	}
	

}

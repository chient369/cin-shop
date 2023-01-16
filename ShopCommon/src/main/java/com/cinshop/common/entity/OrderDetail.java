package com.cinshop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_num")
	private Order order;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;
	private Integer subTotal;

	public OrderDetail() {

	}

	public OrderDetail(Integer id) {
		super();
		this.id = id;
	}

	

	public OrderDetail(Order order, Product product, Integer quantity, Integer subTotal) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Integer subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "OrderDetail [product=" + product + ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
	}

}

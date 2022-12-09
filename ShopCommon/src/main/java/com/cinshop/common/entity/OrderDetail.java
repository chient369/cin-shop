package com.cinshop.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToMany
	@JoinTable(name = "order_product",
				joinColumns = @JoinColumn(name = "order_detail_id"),
				inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> products = new HashSet<>();

	private Integer quantity;
	private Integer subTotal;

	public OrderDetail() {

	}

	public OrderDetail(Integer id) {
		super();
		this.id = id;
	}

	public OrderDetail(Order order, Set<Product> products, Integer quantity, Integer subTotal) {
		super();
		this.order = order;
		this.products = products;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
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

}

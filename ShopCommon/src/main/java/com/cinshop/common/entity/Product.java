package com.cinshop.common.entity;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "detail_id")
	private ProductDetail productDetail;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "size_id")
	private Size size;

	private Integer stockAmount;

	public Product() {

	}

	public Product(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Integer getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(Integer stockAmount) {
		this.stockAmount = stockAmount;
	}
	public Integer getPrice() {
		return this.productDetail.getPrice();
	}
	
	@Transient
	public String getMainImage() {
		return productDetail.getMainImage();
	}
	@Transient
	public String getName() {
		return productDetail.getName();
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, productDetail, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(color, other.color) && Objects.equals(productDetail, other.productDetail)
				&& Objects.equals(size, other.size);
	}

	
	

}

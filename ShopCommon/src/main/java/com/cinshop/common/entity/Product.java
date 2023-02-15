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

	@OneToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToOne
	@JoinColumn(name = "size_id")
	private Size size;

	private Integer stockAmount;

	public Product() {

	}

	public Product(ProductDetail productDetail, Color color, Size size, Integer stockAmount) {
		super();
		this.productDetail = productDetail;
		this.color = color;
		this.size = size;
		this.stockAmount = stockAmount;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
	

}

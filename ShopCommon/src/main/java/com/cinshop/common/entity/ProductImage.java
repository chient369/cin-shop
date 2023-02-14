package com.cinshop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Integer id;

	@Column(name = "image_name", length = 255, nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "detail_id")
	private ProductDetail productDetail;

	public ProductImage() {
	}
	
	

	public ProductImage(String name) {
		super();
		this.name = name;
	}



	public ProductImage(String name, ProductDetail productDetail) {
		super();
		this.name = name;
		this.productDetail = productDetail;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

}

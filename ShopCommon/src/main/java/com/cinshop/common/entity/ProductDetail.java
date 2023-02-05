package com.cinshop.common.entity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

	@Id
	@Column(name = "detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 15, unique = true, nullable = false)
	private String code;

	@Column(length = 256, nullable = false)
	private String name;

	@Column(length = 4096)
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@Column(nullable = false)
	private Integer price;

	private Boolean enable;

	@Column(name = "create_time", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	@Column(name = "main_image", length = 256, nullable = false)
	private String mainImage;

	@OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<>();

	@OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductImage> images = new ArrayList<>();

	@OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FavoriteProduct> favoriteProduct = new ArrayList<>();
	
	private float avgVote;
	private boolean favoriteChecked = false;

	public ProductDetail() {
	}

	public ProductDetail(Integer detailId) {
		this.id = detailId;
	}
	
	public List<FavoriteProduct> getFavoriteProduct() {
		return favoriteProduct;
	}

	public void setFavoriteProduct(List<FavoriteProduct> favoriteProduct) {
		this.favoriteProduct = favoriteProduct;
	}
	
	public boolean isFavoriteChecked() {
		return favoriteChecked;
	}

	public void setFavoriteChecked(boolean favoriteChecked) {
		this.favoriteChecked = favoriteChecked;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public float getAvgVote() {
		return avgVote;
	}

	public void setAvgVote(float avgVote) {
		this.avgVote = avgVote;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Transient
	public String getPriceCurrency() {
		return "¥ "+this.price;
	}
	@Transient
	public String getCreateDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
	   return formatter.format(this.createTime);
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
		ProductDetail other = (ProductDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description
				+ ", category=" + category + ", brand=" + brand + ", price=" + price + ", enable=" + enable
				+ ", createTime=" + createTime + ", mainImage=" + mainImage + ", products=" + products + ", images="
				+ images + ", reviews=" + reviews + "]";
	}

}

package com.cinshop.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex {

	@Id
	@Column(name = "sex_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sex_Id;
	
	@Column(name = "sex_name", length = 15, nullable = false)
	private String sexName;
	
	@OneToOne(mappedBy="sex", cascade=CascadeType.ALL)
	private Customer customer;
	
	public Sex() {
		
	}
	public Sex(Integer id) {
		super();
		this.sex_Id = id;
	}


	public Integer getSex_id() {
		return sex_Id;
	}
	public void setSex_id(Integer sex_Id) {
		this.sex_Id = sex_Id;
	}
	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

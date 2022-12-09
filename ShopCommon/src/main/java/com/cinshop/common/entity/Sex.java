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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sex_id;
	
	@Column(name = "sex_name", length = 15, nullable = true)
	private String sexName;
	
	@OneToOne(mappedBy="sex", cascade=CascadeType.ALL)
	private Customer customer;
	
	public Sex() {
		
	}
	
	//テスト用
	public Sex(String name) {
		this.sexName = name;
	}

	public Integer getSex_id() {
		return sex_id;
	}

	public void setSex_id(Integer sex_id) {
		this.sex_id = sex_id;
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

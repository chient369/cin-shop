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
	private Integer sexId;
	
	@Column(name = "sex_name", length = 15, nullable = false)
	private String sexName;
	
	@OneToOne(mappedBy="sex", cascade=CascadeType.ALL)
	private Customer customer;
	
	public Sex() {
		
	}

	public Integer getSex_id() {
		return sexId;
	}

	public void setSex_id(Integer sexId) {
		this.sexId = sexId;
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

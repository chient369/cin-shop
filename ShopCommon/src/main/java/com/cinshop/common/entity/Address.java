package com.cinshop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer id;

	@Column(name = "post_code", length = 20, nullable = false)
	private String postCode;

	@Column(name = "first_address", length = 45, nullable = false)
	private String firstAddress;

	@Column(name = "last_address", length = 45, nullable = false)
	private String lastAddress;

	public Address() {

	}
	

	public Address(String postCode, String firstAddress, String lastAddress) {
		super();
		this.postCode = postCode;
		this.firstAddress = firstAddress;
		this.lastAddress = lastAddress;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(String firstAddress) {
		this.firstAddress = firstAddress;
	}

	public String getLastAddress() {
		return lastAddress;
	}

	public void setLastAddress(String lastAddress) {
		this.lastAddress = lastAddress;
	}

	public String getFullAddress() {
		return "〒"+this.postCode + " " +this.firstAddress + " " + this.lastAddress;
	}

	@Override
	public String toString() {
		return "Address [postCode=" + postCode + ", firstAddress=" + firstAddress + ", lastAddress=" + lastAddress
				+ "]";
	}
	
}

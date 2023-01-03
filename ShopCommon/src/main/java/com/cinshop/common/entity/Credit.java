package com.cinshop.common.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "credit")
public class Credit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "card_num", length = 64, unique = true)
	private String cardNum;

	@Column(name = "owner_name", length = 64, unique = true)
	private String ownerName;

	@Column(name = "expiry_date", unique = true)
	@Temporal(TemporalType.DATE)
	private Date expiry;

	@Column(name = "secur_code", length = 6, unique = true)
	private String securCode;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Credit(Integer id) {
		super();
		this.id = id;
	}

	public Credit() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getSecurCode() {
		return securCode;
	}

	public void setSecurCode(String securCode) {
		this.securCode = securCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
